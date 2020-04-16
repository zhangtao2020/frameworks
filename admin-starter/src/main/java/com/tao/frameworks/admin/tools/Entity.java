package com.tao.frameworks.admin.tools;

import java.util.ArrayList;
import java.util.List;

public class Entity {

    public String poundSign = "#";
    public String dollarSign = "$";
    public String basePackageName;
    public String entityPackageName;
    public String dtoPackageName;
    public String paramPackage;
    public String abstractEntityPackageName;
    public String daoPackageName;
    public String servicePackageName;
    public String actionPackageName;
    public String className;
    public String classInstanceName;
    public String tableName;
    public String cloumnName;
    public String idName;
    public String idColumn;
    public String idType;
    public String idSimpleType;
    public String idGetMethod;
    public String idSetMethod;
    public boolean idAutoIncrement = true;
    public boolean hasDateType = false;
    public boolean hasDecimalType = false;
    public boolean hasSetType = false;
    public boolean manyToMany = false;
    public boolean hasHibernateVersion = false;
    public boolean hasFetchType = false;
    public boolean hasJoinColumn = false;
    public String author;
    public String createTime;
    public String versionLock;
    public List<Property> propList = new ArrayList();
    public List<SetProperty> sPropList = new ArrayList();
    public List<AliasProperty> aPropList = new ArrayList();

    public Entity() {
    }

    public String getDollarSign() {
        return this.dollarSign;
    }

    public Entity setDollarSign(String dollarSign) {
        this.dollarSign = dollarSign;
        return this;
    }

    public String getParamPackage() {
        return this.paramPackage;
    }

    public Entity setParamPackage(String paramPackage) {
        this.paramPackage = paramPackage;
        return this;
    }

    public String getVersionLock() {
        return this.versionLock;
    }

    public void setVersionLock(String versionLock) {
        this.versionLock = versionLock;
    }

    public String getBasePackageName() {
        return this.basePackageName;
    }

    public void setBasePackageName(String basePackageName) {
        this.basePackageName = basePackageName;
    }

    public String getEntityPackageName() {
        return this.entityPackageName;
    }

    public void setEntityPackageName(String entityPackageName) {
        this.entityPackageName = entityPackageName;
    }

    public String getDtoPackageName() {
        return this.dtoPackageName;
    }

    public void setDtoPackageName(String dtoPackageName) {
        this.dtoPackageName = dtoPackageName;
    }

    public String getAbstractEntityPackageName() {
        return this.abstractEntityPackageName;
    }

    public void setAbstractEntityPackageName(String abstractEntityPackageName) {
        this.abstractEntityPackageName = abstractEntityPackageName;
    }

    public String getServicePackageName() {
        return this.servicePackageName;
    }

    public void setServicePackageName(String servicePackageName) {
        this.servicePackageName = servicePackageName;
    }

    public String getActionPackageName() {
        return this.actionPackageName;
    }

    public void setActionPackageName(String actionPackageName) {
        this.actionPackageName = actionPackageName;
    }

    public String getClassInstanceName() {
        return this.classInstanceName;
    }

    public void setClassInstanceName(String classInstanceName) {
        this.classInstanceName = classInstanceName;
    }

    public String getClassName() {
        return this.className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getTableName() {
        return this.tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdName() {
        return this.idName;
    }

    public void setIdName(String idName) {
        this.idName = idName;
    }

    public String getIdColumn() {
        return this.idColumn;
    }

    public void setIdColumn(String idColumn) {
        this.idColumn = idColumn;
    }

    public String getDaoPackageName() {
        return this.daoPackageName;
    }

    public void setDaoPackageName(String daoPackageName) {
        this.daoPackageName = daoPackageName;
    }

    public String getIdType() {
        return this.idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdSimpleType() {
        return this.idSimpleType;
    }

    public void setIdSimpleType(String idSimpleType) {
        this.idSimpleType = idSimpleType;
    }

    public String getIdGetMethod() {
        return this.idGetMethod;
    }

    public void setIdGetMethod(String idGetMethod) {
        this.idGetMethod = idGetMethod;
    }

    public String getIdSetMethod() {
        return this.idSetMethod;
    }

    public void setIdSetMethod(String idSetMethod) {
        this.idSetMethod = idSetMethod;
    }

    public boolean isIdAutoIncrement() {
        return this.idAutoIncrement;
    }

    public void setIdAutoIncrement(boolean idAutoIncrement) {
        this.idAutoIncrement = idAutoIncrement;
    }

    public boolean isHasDateType() {
        return this.hasDateType;
    }

    public void setHasDateType(boolean hasDateType) {
        this.hasDateType = hasDateType;
    }

    public boolean isHasDecimalType() {
        return this.hasDecimalType;
    }

    public void setHasDecimalType(boolean hasDecimalType) {
        this.hasDecimalType = hasDecimalType;
    }

    public boolean isHasSetType() {
        return this.hasSetType;
    }

    public void setHasSetType(boolean hasSetType) {
        this.hasSetType = hasSetType;
    }

    public boolean isManyToMany() {
        return this.manyToMany;
    }

    public void setManyToMany(boolean manyToMany) {
        this.manyToMany = manyToMany;
    }

    public boolean isHasHibernateVersion() {
        return this.hasHibernateVersion;
    }

    public void setHasHibernateVersion(boolean hasHibernateVersion) {
        this.hasHibernateVersion = hasHibernateVersion;
    }

    public List<Property> getPropList() {
        return this.propList;
    }

    public void setPropList(List<Property> propList) {
        this.propList = propList;
    }

    public List<SetProperty> getsPropList() {
        return this.sPropList;
    }

    public void setsPropList(List<SetProperty> sPropList) {
        this.sPropList = sPropList;
    }

    public List<AliasProperty> getaPropList() {
        return this.aPropList;
    }

    public void setaPropList(List<AliasProperty> aPropList) {
        this.aPropList = aPropList;
    }

    public boolean isHasFetchType() {
        return this.hasFetchType;
    }

    public void setHasFetchType(boolean hasFetchType) {
        this.hasFetchType = hasFetchType;
    }

    public boolean isHasJoinColumn() {
        return this.hasJoinColumn;
    }

    public void setHasJoinColumn(boolean hasJoinColumn) {
        this.hasJoinColumn = hasJoinColumn;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCloumnName() {
        return this.cloumnName;
    }

    public void setCloumnName(String cloumnName) {
        this.cloumnName = cloumnName;
    }

    public String getPoundSign() {
        return this.poundSign;
    }

    public void setPoundSign(String poundSign) {
        this.poundSign = poundSign;
    }

    public static class AliasProperty {
        public String primaryTableName;
        public String primaryClassName;
        public String columnName;
        public String propName;
        public String getMethod;
        public String setMethod;

        public AliasProperty() {
        }

        public String getPrimaryTableName() {
            return this.primaryTableName;
        }

        public void setPrimaryTableName(String primaryTableName) {
            this.primaryTableName = primaryTableName;
        }

        public String getPrimaryClassName() {
            return this.primaryClassName;
        }

        public void setPrimaryClassName(String primaryClassName) {
            this.primaryClassName = primaryClassName;
        }

        public String getColumnName() {
            return this.columnName;
        }

        public void setColumnName(String columnName) {
            this.columnName = columnName;
        }

        public String getPropName() {
            return this.propName;
        }

        public void setPropName(String propName) {
            this.propName = propName;
        }

        public String getGetMethod() {
            return this.getMethod;
        }

        public void setGetMethod(String getMethod) {
            this.getMethod = getMethod;
        }

        public String getSetMethod() {
            return this.setMethod;
        }

        public void setSetMethod(String setMethod) {
            this.setMethod = setMethod;
        }
    }

    public static class SetProperty {
        public String foreignTableName;
        public String foreignClassName;
        public String foreignColumnName;
        public String manyForeignColumnName;
        public String propName;
        public String getMethod;
        public String setMethod;
        public boolean manyToMany = false;
        public String manyToManyTable;

        public SetProperty() {
        }

        public String getManyForeignColumnName() {
            return this.manyForeignColumnName;
        }

        public void setManyForeignColumnName(String manyForeignColumnName) {
            this.manyForeignColumnName = manyForeignColumnName;
        }

        public String getForeignTableName() {
            return this.foreignTableName;
        }

        public void setForeignTableName(String foreignTableName) {
            this.foreignTableName = foreignTableName;
        }

        public String getForeignClassName() {
            return this.foreignClassName;
        }

        public void setForeignClassName(String foreignClassName) {
            this.foreignClassName = foreignClassName;
        }

        public String getForeignColumnName() {
            return this.foreignColumnName;
        }

        public void setForeignColumnName(String foreignColumnName) {
            this.foreignColumnName = foreignColumnName;
        }

        public String getPropName() {
            return this.propName;
        }

        public void setPropName(String propName) {
            this.propName = propName;
        }

        public String getGetMethod() {
            return this.getMethod;
        }

        public void setGetMethod(String getMethod) {
            this.getMethod = getMethod;
        }

        public String getSetMethod() {
            return this.setMethod;
        }

        public void setSetMethod(String setMethod) {
            this.setMethod = setMethod;
        }

        public boolean isManyToMany() {
            return this.manyToMany;
        }

        public void setManyToMany(boolean manyToMany) {
            this.manyToMany = manyToMany;
        }

        public String getManyToManyTable() {
            return this.manyToManyTable;
        }

        public void setManyToManyTable(String manyToManyTable) {
            this.manyToManyTable = manyToManyTable;
        }
    }

    public static class Property {
        public String propName;
        public String column;
        public String propType;
        public String notAllowNull;
        public String note;
        public String simpleType;
        public String getMethod;
        public String setMethod;
        public boolean manyToOne = false;

        public Property() {
        }

        public boolean isManyToOne() {
            return this.manyToOne;
        }

        public void setManyToOne(boolean manyToOne) {
            this.manyToOne = manyToOne;
        }

        public String getPropName() {
            return this.propName;
        }

        public void setPropName(String propName) {
            this.propName = propName;
        }

        public String getColumn() {
            return this.column;
        }

        public void setColumn(String column) {
            this.column = column;
        }

        public String getPropType() {
            return this.propType;
        }

        public void setPropType(String propType) {
            this.propType = propType;
        }

        public String getNotAllowNull() {
            return this.notAllowNull;
        }

        public void setNotAllowNull(String notAllowNull) {
            this.notAllowNull = notAllowNull;
        }

        public String getNote() {
            return this.note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getSimpleType() {
            return this.simpleType;
        }

        public void setSimpleType(String simpleType) {
            this.simpleType = simpleType;
        }

        public String getGetMethod() {
            return this.getMethod;
        }

        public void setGetMethod(String getMethod) {
            this.getMethod = getMethod;
        }

        public String getSetMethod() {
            return this.setMethod;
        }

        public void setSetMethod(String setMethod) {
            this.setMethod = setMethod;
        }
    }
}
