package com.huateng.batch.BeanRowMapperTool;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.support.JdbcUtils;
import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;

/*
 * 
 * 根据Class 动态生成 sql 以及属性与数据库字段
 * tip:Class 名称以及字段严格遵守驼峰规则
 * ygq
 */
public class BeanRowMapper<T>{

	/** Logger available to subclasses. */
	protected final Logger logger = LoggerFactory.getLogger(getClass());

	/** The class we are mapping to. */
	@Nullable
	private Class<T> mappedClass;

	/** Map of the fields we provide mapping for. */
	@Nullable
	private Map<String, String> mappedFields;

	/** Set of bean properties we provide mapping for. */
	@Nullable
	private List<String> mappedProperties;


	/**
	 * Create a new {@code BeanPropertyRowMapper} for bean-style configuration.
	 * @see #setMappedClass
	 * @see #setCheckFullyPopulated
	 */

	/**
	 * Create a new {@code BeanPropertyRowMapper}, accepting unpopulated
	 * properties in the target bean.
	 * <p>Consider using the {@link #newInstance} factory method instead,
	 * which allows for specifying the mapped type once only.
	 * @param mappedClass the class that each row should be mapped to
	 */
	public BeanRowMapper(Class<T> mappedClass) {
		initialize(mappedClass);
	}



	/**
	 * Set the class that each row should be mapped to.
	 */
	public void setMappedClass(Class<T> mappedClass) {
		if (this.mappedClass == null) {
			initialize(mappedClass);
		}
		else {
			if (this.mappedClass != mappedClass) {
				throw new InvalidDataAccessApiUsageException("The mapped class can not be reassigned to map to " +
						mappedClass + " since it is already providing mapping for " + this.mappedClass);
			}
		}
	}

	/**
	 * Get the class that we are mapping to.
	 */
	@Nullable
	public final Class<T> getMappedClass() {
		return this.mappedClass;
	}


	/**
	 * Initialize the mapping meta-data for the given class.
	 * @param mappedClass the mapped class
	 */
	protected void initialize(Class<T> mappedClass) {
		this.mappedClass = mappedClass;
		this.mappedFields = new HashMap<>();
		this.mappedProperties = new ArrayList<>();
		PropertyDescriptor[] pds = BeanUtils.getPropertyDescriptors(mappedClass);
		for (PropertyDescriptor pd : pds) {
			if (pd.getWriteMethod() != null) {
				String underscoredName = underscoreName(pd.getName());
				this.mappedFields.put(pd.getName(), underscoredName);
				
				if (!lowerCaseName(pd.getName()).equals(underscoredName)) {
					this.mappedFields.put(pd.getName(),underscoredName);
				}
				this.mappedProperties.add(pd.getName());
			}
		}
	}

	/**
	 * Convert a name in camelCase to an underscored name in lower case.
	 * Any upper case letters are converted to lower case with a preceding underscore.
	 * @param name the original name
	 * @return the converted name
	 * @since 4.2
	 * @see #lowerCaseName
	 */
	protected String underscoreName(String name) {
		if (!StringUtils.hasLength(name)) {
			return "";
		}
		StringBuilder result = new StringBuilder();
		result.append(lowerCaseName(name.substring(0, 1)));
		for (int i = 1; i < name.length(); i++) {
			String s = name.substring(i, i + 1);
			String slc = lowerCaseName(s);
			if (!s.equals(slc)) {
				result.append("_").append(slc);
			}
			else {
				result.append(s);
			}
		}
		return result.toString();
	}

	/**
	 * Convert the given name to lower case.
	 * By default, conversions will happen within the US locale.
	 * @param name the original name
	 * @return the converted name
	 * @since 4.2
	 */
	protected String lowerCaseName(String name) {
		return name.toLowerCase(Locale.US);
	}


	/**
	 * Retrieve a JDBC object value for the specified column.
	 * <p>The default implementation calls
	 * {@link JdbcUtils#getResultSetValue(java.sql.ResultSet, int, Class)}.
	 * Subclasses may override this to check specific value types upfront,
	 * or to post-process values return from {@code getResultSetValue}.
	 * @param rs is the ResultSet holding the data
	 * @param index is the column index
	 * @param pd the bean property that each result object is expected to match
	 * @return the Object value
	 * @throws SQLException in case of extraction failure
	 * @see org.springframework.jdbc.support.JdbcUtils#getResultSetValue(java.sql.ResultSet, int, Class)
	 */
	@Nullable
	protected Object getColumnValue(ResultSet rs, int index, PropertyDescriptor pd) throws SQLException {
		return JdbcUtils.getResultSetValue(rs, index, pd.getPropertyType());
	}


	/**
	 * Static factory method to create a new {@code BeanPropertyRowMapper}
	 * (with the mapped class specified only once).
	 * @param mappedClass the class that each row should be mapped to
	 */
	public static <T> BeanPropertyRowMapper<T> newInstance(Class<T> mappedClass) {
		return new BeanPropertyRowMapper<>(mappedClass);
	}

	

	

	public Map<String, String> getMappedFields() {
		return mappedFields;
	}

	public void setMappedFields(Map<String, String> mappedFields) {
		this.mappedFields = mappedFields;
	}

	public List<String> getMappedProperties() {
		return mappedProperties;
	}

	public void setMappedProperties(List<String> mappedProperties) {
		this.mappedProperties = mappedProperties;
	}

	public String getInsertSql(Class<T> mappedClass) {
		String value ="";
		String colnmus="";
		for(int i =0;i<this.mappedProperties.size();i++) {
			if( i!=this.mappedProperties.size()-1) {
				colnmus = colnmus+this.mappedFields.get(this.mappedProperties.get(i))+",";
				value = value+"?"+",";
			}else {
				colnmus = colnmus+this.mappedFields.get(this.mappedProperties.get(i));
				value = value+"?";
			}
		}
		
		String sql ="insert into  "+underscoreName(this.getMappedClass().getName().substring(this.getMappedClass().getName().lastIndexOf(".")+1))+"("+colnmus+")  values("+value+")" ;
	
		return sql;
	}
	
	public String getInsertSqlBean(Class<T> mappedClass) {
		String value ="";
		String colnmus="";
		for(int i =0;i<this.mappedProperties.size();i++) {
			if( i!=this.mappedProperties.size()-1) {
				colnmus = colnmus+this.mappedFields.get(this.mappedProperties.get(i))+",";
				value = value+":"+this.mappedProperties.get(i)+",";
			}else {
				colnmus = colnmus+this.mappedFields.get(this.mappedProperties.get(i));
				value = value+":"+this.mappedProperties.get(i);
			}
		}
		
		String sql ="insert into  "+underscoreName(this.getMappedClass().getName().substring(this.getMappedClass().getName().lastIndexOf(".")+1))+"("+colnmus+")  values("+value+")" ;
	
		return sql;
	}
	
	
	 /**
     * 使用java反射机制，动态获取对象的属性和参数值，排除值为null的情况，并按字典序排序
     * @param object
     * @return
     * @throws Exception
     */
    public Map<String, String> getSortMap(Object object) throws Exception{
           Field[] fields = object.getClass().getDeclaredFields();
           Map<String, String> map = new HashMap<String, String>();
           //迭代属性 
           for(Field field : fields){
                String name = field.getName();
             //   String methodName = "get" + name.replaceFirst(name.substring(0, 1), name.substring(0, 1)
                   //     .toUpperCase());
                // 调用getter方法获取属性值
//                Method getter = object.getClass().getMethod(methodName);
//                String value =  getter.invoke(object)+"";
                
                //通过get方法直接获取属性值
                field.setAccessible(true);
                Object value = field.get(object);
                if (value != null){
                    map.put(name, value.toString());
                }else {
                	map.put(name, "");
                }
               /* System.out.println("字段名："+name);
                System.out.println("字段值："+field.get(object));
                System.out.println("字段java语言修饰符："+field.getModifiers());
                System.out.println("字段类型："+field.getType());
                System.out.println("");*/
           }
/*
           Map<String, String> sortMap = new TreeMap<String, String>(
                   new Comparator<String>() {

                       @Override
                       public int compare(String arg0, String arg1) {
                          
                           return arg0.compareTo(arg1);
                       }
                   });
           sortMap.putAll(map);*/
           return map;
       }
   
}

