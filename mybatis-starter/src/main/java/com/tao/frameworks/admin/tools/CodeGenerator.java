package com.tao.frameworks.admin.tools;

import freemarker.template.Configuration;
import freemarker.template.Template;
import org.apache.commons.io.FileUtils;

import java.io.*;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.*;

public class CodeGenerator {

    public static String PROJECT_ABSOLUTE_DIR = null;
    public static String BASE_PACKAGE = "com.tao.frameworks.mybatis";
    public static String SUB_PACKAGE = "";
    public static String DB_URL = "jdbc:mysql://47.99.237.229:3306/test?useSSL=true";
    public static String DB_USERNAME = "root";
    public static String DB_PASSWORD = "root";
    public static String TOBE_GENERATE_TABLES = "";
    public static String AUTHOR = "zt";
    private static CodeGenerator.Config config = new CodeGenerator.Config();
    private static final Map<String, Class> defaultTypeMap = new HashMap<String, Class>(){
        {
            this.put("LONGTEXT", String.class);
            this.put("INT", Integer.class);
            this.put("BIGINT", Long.class);
            this.put("DATE", Date.class);
            this.put("DATETIME", Date.class);
            this.put("TEXT", String.class);
            this.put("VARCHAR", String.class);
            this.put("DECIMAL", BigDecimal.class);
            this.put("BIT", Boolean.class);
            this.put("TIMESTAMP", Date.class);
            this.put("FLOAT", Float.class);
            this.put("DOUBLE", Double.class);
            this.put("MEDIUMTEXT", String.class);
            this.put("CHAR", String.class);
            this.put("ENUM", String.class);
            this.put("BLOB", String.class);
            this.put("TINYINT", Integer.class);
        }
    };

    public static void main(String[] args) {
        config.subPackage = SUB_PACKAGE != null && SUB_PACKAGE.trim().length() != 0 ? "." + SUB_PACKAGE : "";
        config.entityPackage = BASE_PACKAGE + config.subPackage + ".entity";
        config.dtoPackage = BASE_PACKAGE + config.subPackage + ".dto";
        config.paramPackage = BASE_PACKAGE + config.subPackage + ".params";
        config.daoPackage = BASE_PACKAGE + config.subPackage + ".dao";
        config.servicePackage = BASE_PACKAGE + config.subPackage + ".service";
        config.actionPackage = BASE_PACKAGE + config.subPackage + ".controller";

        try {
            Connection conn = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            DatabaseMetaData dbmd = conn.getMetaData();
            List<Entity> entityList = new ArrayList<>();
            if ("".equals(TOBE_GENERATE_TABLES)) {
                entityList = getTableEntities(dbmd);
            } else {
                entityList.add(buildTableEntity(TOBE_GENERATE_TABLES, dbmd));
            }

            for (Entity entity : entityList) {
                generate(config.entityPackage, "/src/main/java/", entity.className + ".java", "entity.ftl", entity, true);
                generate(config.dtoPackage, "/src/main/java/", entity.className + "Dto.java", "entitydto.ftl", entity, false);
                generate(config.paramPackage, "/src/main/java/", entity.className + "Param.java", "entityparam.ftl", entity, false);
                generate(config.actionPackage, "/src/main/java/", entity.className + "Controller.java", "controller.ftl", entity, false);
                generate(config.servicePackage, "/src/main/java/", entity.className + "Service.java", "service.ftl", entity, false);
                generate(config.daoPackage, "/src/main/java/", entity.className + "Mapper.java", "IMapperdao.ftl", entity, false);
                generate("", "/src/main/resources/mybatis/" + SUB_PACKAGE + "/", entity.className + "Mapper.xml", "mapper.xml.ftl", entity, true);
            }
        } catch (Throwable e) {
            e.printStackTrace();
        }

    }

    /**
     * @param packageName  包
     * @param prefix       prefix
     * @param suffix       suffix
     * @param templateName templateName
     * @param entity       entity
     * @param cover        true/覆盖； false/不覆盖
     * @throws Exception exception
     */
    private static void generate(String packageName, String prefix, String suffix,
                                 String templateName, Entity entity, boolean cover) throws Exception {
        String packagePath = packageName.replace('.', '/');
        String projectDir = PROJECT_ABSOLUTE_DIR == null || PROJECT_ABSOLUTE_DIR.trim().length() == 0 ? System.getProperty("user.dir") : PROJECT_ABSOLUTE_DIR;

        String path = projectDir + prefix + packagePath + "/" + suffix;
        File file = new File(path);
        List<String> lines = null;
        if (file.exists()) {
            if (!cover) {
                return;
            } else {
                lines = readNotReplaceableLines(file);
            }
        }
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }

        Configuration cfg = new Configuration(Configuration.DEFAULT_INCOMPATIBLE_IMPROVEMENTS);
        cfg.setClassForTemplateLoading(CodeGenerator.class, "/mybatisstarterftl/");

        Template hbmTemplate = cfg.getTemplate(templateName);

        Writer out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8));
        HashMap<String, Object> dataMap = new HashMap<>();
        dataMap.put("entity", entity);
        hbmTemplate.process(dataMap, out);

        out.close();

        if (lines != null && lines.size() > 0) {
            replaceFile(file, lines);
        }
    }


    private static List<Entity> getTableEntities(DatabaseMetaData dbmd) throws SQLException {
        ResultSet rs = dbmd.getTables(null, null, null, new String[]{"table"});
        ArrayList tables = new ArrayList();
        while (rs.next()){
            System.out.println(rs.getString("TABLE_NAME"));
            tables.add(rs.getString("TABLE_NAME"));
        }
        rs.close();
        List<Entity> entityList = new ArrayList<>();
        tables.forEach(s->{
            String table = (String)s;
            //Entity entity
        });
        return entityList;
    }


    private static Entity buildTableEntity(String table, DatabaseMetaData dbmd) throws Exception {
        Entity entity = new Entity();
        entity.author = AUTHOR;
        entity.createTime = (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date());
        entity.basePackageName = BASE_PACKAGE;
        entity.entityPackageName = config.entityPackage;
        entity.dtoPackageName = config.dtoPackage;
        entity.paramPackage = config.paramPackage;
        entity.daoPackageName = config.daoPackage;
        entity.servicePackageName = config.servicePackage;
        entity.actionPackageName = config.actionPackage;
        entity.tableName = table;
        entity.className = fillUpperName(removePrefix(table, ""));
        entity.classInstanceName = fillLowerName(removePrefix(table, ""));

        ResultSet rs;
        for(rs = dbmd.getPrimaryKeys((String)null, (String)null, table); rs.next(); entity.idColumn = rs.getString("COLUMN_NAME")) {
            if (entity.idColumn != null) {
                entity.manyToMany = true;
                return entity;
            }
        }

        rs.close();
        rs = dbmd.getImportedKeys((String)null, (String)null, table);

        String type;
        while(rs.next()) {
            Entity.AliasProperty aprop = new Entity.AliasProperty();
            aprop.primaryTableName = rs.getString("PKTABLE_NAME");
            aprop.primaryClassName = fillUpperName(removePrefix(aprop.primaryTableName, "fk_"));
            aprop.columnName = rs.getString("FKCOLUMN_NAME");
            type = fillLowerName(aprop.columnName);
            aprop.propName = type.endsWith("Id") ? type.substring(0, type.length() - 2) : type;
            aprop.getMethod = "get" + fillFirstUpper(aprop.propName);
            aprop.setMethod = "set" + fillFirstUpper(aprop.propName);
            entity.aPropList.add(aprop);
        }

        rs.close();
        rs = dbmd.getExportedKeys((String)null, (String)null, table);

        while(rs.next()) {
            if (table.equals(rs.getString("PKTABLE_NAME"))) {
                Entity.SetProperty sprop = new Entity.SetProperty();
                sprop.foreignTableName = rs.getString("FKTABLE_NAME");
                sprop.foreignClassName = fillUpperName(removePrefix(sprop.foreignTableName, "fk_"));
                sprop.foreignColumnName = rs.getString("FKCOLUMN_NAME");
                sprop.propName = fillFirstLower(sprop.foreignClassName) + "Set";
                sprop.getMethod = buildGetMethod(sprop.propName);
                sprop.setMethod = buildSetMethod(sprop.propName);
                buildManyToMany(sprop, dbmd);
                entity.sPropList.add(sprop);
                entity.hasSetType = true;
            }
        }

        rs.close();
        rs = dbmd.getColumns((String)null, (String)null, table, "%");

        while(true) {
            while(rs.next()) {
                String columnName = rs.getString("COLUMN_NAME");
                if ("version".equalsIgnoreCase(columnName)) {
                    entity.hasHibernateVersion = true;
                    entity.versionLock = "#{version}";
                } else {
                    entity.cloumnName = columnName;
                    type = rs.getString("TYPE_NAME");
                    Class kls = (Class)defaultTypeMap.get(type);
                    if (kls == null) {
                        System.out.println("The column {" + columnName + "} has unknown Database DataType: " + type + ", String.class are replaced default,  you can call CodeGenerator.addTypeMapping(.., ..) to correct it also");
                        kls = String.class;
                    }

                    if (entity.idColumn == null) {
                        entity.idColumn = columnName;
                        entity.idName = fillLowerName(columnName);
                        entity.idType = kls.getName();
                        entity.idSimpleType = kls.getSimpleName();
                        entity.idGetMethod = buildGetMethod(entity.idName);
                        entity.idSetMethod = buildSetMethod(entity.idName);
                        entity.idAutoIncrement = !"NO".equals(rs.getString("IS_AUTOINCREMENT"));
                    } else if (columnName.equals(entity.idColumn)) {
                        entity.idName = fillLowerName(entity.idColumn);
                        entity.idType = kls.getName();
                        entity.idSimpleType = kls.getSimpleName();
                        entity.idGetMethod = buildGetMethod(entity.idName);
                        entity.idSetMethod = buildSetMethod(entity.idName);
                        entity.idAutoIncrement = !"NO".equals(rs.getString("IS_AUTOINCREMENT"));
                    } else {
                        Entity.Property prop = new Entity.Property();
                        prop.column = columnName;
                        prop.propName = fillLowerName(prop.column);
                        prop.propType = kls.getName();
                        prop.simpleType = kls.getSimpleName();
                        if ("BigDecimal".equals(prop.simpleType)) {
                            entity.hasDecimalType = true;
                        } else if ("Date".equals(prop.simpleType)) {
                            entity.hasDateType = true;
                        }

                        prop.notAllowNull = "NO".equals(rs.getString("IS_NULLABLE")) ? (rs.getString("COLUMN_DEF") == null ? "true" : "false") : "false";
                        prop.note = rs.getString("REMARKS");
                        prop.getMethod = buildGetMethod(prop.propName, "Boolean".equals(prop.simpleType));
                        prop.setMethod = buildSetMethod(prop.propName);
                        Iterator var8 = entity.aPropList.iterator();

                        while(var8.hasNext()) {
                            Entity.AliasProperty aprop = (Entity.AliasProperty)var8.next();
                            if (prop.column.equals(aprop.columnName)) {
                                prop.manyToOne = true;
                                break;
                            }
                        }

                        entity.propList.add(prop);
                    }
                }
            }

            rs.close();
            if (!entity.hasHibernateVersion) {
                System.out.println("Warning: table[" + entity.tableName + "] dosn't have a version column, 不需要乐观锁忽略");
            }

            return entity;
        }
    }

    private static String fillUpperName(String underline) {
        String lower = underline2camel(underline);
        return fillFirstUpper(lower);
    }

    private static String underline2camel(String underline) {
        StringBuilder sb = new StringBuilder();
        String temp = underline;

        while(true) {
            int index = temp.indexOf("_");
            if (index == -1) {
                sb.append(temp);
                return sb.toString();
            }

            sb.append(temp.substring(0, index));
            sb.append(temp.substring(index + 1, index + 2).toUpperCase());
            temp = temp.substring(index + 2);
        }
    }

    private static String fillFirstUpper(String name) {
        return name.substring(0, 1).toUpperCase() + name.substring(1);
    }

    private static String fillLowerName(String underline) {
        return underline2camel(underline);
    }

    private static String removePrefix(String tableName, String prefix) {
        return tableName.startsWith(prefix) ? tableName.substring(prefix.length()) : tableName;
    }

    private static String fillFirstLower(String name) {
        return name.substring(0, 1).toLowerCase() + name.substring(1);
    }

    private static String buildGetMethod(String field, boolean isBoolean) {
        return "get" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private static String buildGetMethod(String field) {
        return buildGetMethod(field, false);
    }


    private static String buildSetMethod(String field) {
        return "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
    }

    private static List<String> readNotReplaceableLines(File file) throws IOException {
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        List<String> lines = null;

        boolean start = false;
        String line;
        while ((line = br.readLine()) != null) {
            if (line.contains("//@NotReplaceableStart")) {
                lines = new ArrayList<String>();
                start = true;
            }

            if (start) {
                lines.add(line);
            }

            if (line.contains("//@NotReplaceableEnd")) {
                start = false;
            }

        }
        br.close();
        fr.close();

        return lines;
    }

    private static void replaceFile(File file, List<String> lines) throws IOException {
        File destFile = new File(file.getPath() + ".temp");
        if (!file.exists()) {
            return;
        }
        FileReader scrR = new FileReader(file);
        BufferedReader srcB = new BufferedReader(scrR);

        FileWriter fw = new FileWriter(destFile);
        String lineS = null;

        boolean replace = false;
        while ((lineS = srcB.readLine()) != null) {
            if (lineS.contains("//@NotReplaceableStart")) {
                replace = true;

                for (String change : lines) {
                    fw.write(change + "\r\n");
                }
            }

            if (!replace) {
                fw.write(lineS + "\r\n");
            }

            if (lineS.contains("//@NotReplaceableEnd")) {
                replace = false;
            }
        }

        fw.close();
        srcB.close();
        scrR.close();

        FileUtils.copyFile(destFile, file);
        destFile.delete();
    }


    private static void buildManyToMany(Entity.SetProperty sprop, DatabaseMetaData dbmd) throws Exception {
        ResultSet rs = dbmd.getPrimaryKeys(null, null, sprop.foreignTableName);
        int primaryKeyNumber = 0;
        while (rs.next()) {
            primaryKeyNumber++;
        }
        if (primaryKeyNumber > 1) {
            sprop.manyToMany = true;
            rs.close();

            rs = dbmd.getImportedKeys(null, null, sprop.foreignTableName);
            while (rs.next()) {
                if (!sprop.foreignColumnName.equals(rs.getString("FKCOLUMN_NAME"))) {
                    sprop.manyToManyTable = sprop.foreignTableName;
                    sprop.foreignTableName = rs.getString("PKTABLE_NAME");
                    sprop.foreignClassName = fillUpperName(removePrefix(sprop.foreignTableName, ""));
                    sprop.propName = fillFirstLower(sprop.foreignClassName) + "Set";
                    sprop.getMethod = buildGetMethod(sprop.propName);
                    sprop.setMethod = buildSetMethod(sprop.propName);
                    sprop.manyForeignColumnName = rs.getString("FKCOLUMN_NAME");
                    break;
                }
            }
            rs.close();
        }
    }


    private static class Config {
        private String subPackage;
        private String entityPackage;
        private String dtoPackage;
        private String paramPackage;
        private String daoPackage;
        private String servicePackage;
        private String actionPackage;

        private Config() {
        }
    }

    public static void addTypeMapping(String dbType, Class javaType) {
        defaultTypeMap.put(dbType, javaType);
    }

}
