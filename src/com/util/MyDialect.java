package  com.util;

import java.sql.Types;
import org.hibernate.dialect.MySQL57Dialect;
import org.hibernate.dialect.function.SQLFunctionTemplate;
import org.hibernate.type.StringType;

/**
 * hibernate 语言包
 */
public class MyDialect extends MySQL57Dialect {
	  
    public MyDialect() {  
        super();  
        
        /*org.hibernate.dialect.MySQL5InnoDBDialect
         * hibernate 3使用方式
         * 
        registerHibernateType(Types.LONGVARCHAR, Hibernate.TEXT.getName());        
        registerHibernateType(-1,Hibernate.String.getName());
        registerHibernateType(Types.DATE, Hibernate.DATE.getName());
        registerHibernateType(Types.DATE, Hibernate.STRING.getName());*/
        
        //hibernate 4使用方式
        registerHibernateType(Types.LONGVARCHAR, "String");
        registerHibernateType(Types.DATE, "Date");
        registerHibernateType(Types.DECIMAL,"big_decimal");
        registerFunction("convert",new SQLFunctionTemplate(StringType.INSTANCE,"convert(?1 using gbk)"));
        
        //-----------添加mysql函数
    }  
  
}