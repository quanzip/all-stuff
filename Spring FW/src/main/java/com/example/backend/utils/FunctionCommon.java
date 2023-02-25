package com.example.backend.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import javax.persistence.Tuple;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.security.SecureRandom;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.Normalizer;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.regex.Pattern;

public class FunctionCommon {
    private static final Logger LOGGER = Logger.getLogger(FunctionCommon.class);
    private static final ResourceBundle RESOURCE_BUNDLE = getResourceBundle();
    private static ExecutorService executorService;

    public FunctionCommon() {
    }

    private static ResourceBundle getResourceBundle() {
        try {
            return ResourceBundle.getBundle(Constant.CONFIGFILEPROPERTIES);
        } catch (Exception var1) {
            LOGGER.error("Loi! getResourceBundle: " + var1.getMessage());
            return null;
        }
    }

    private static Boolean checkIsNumber(Object o) {
        return o instanceof Integer || o instanceof Double || o instanceof Float || o instanceof Long;
    }

    public static boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");
    }

    public static List<?> convertToEntity(List<Tuple> input, Class<?> dtoClass) {
        List<Object> arrayList = new ArrayList();
        input.stream().forEach((tuple) -> {
            Map<String, Object> temp = new HashMap();
            tuple.getElements().stream().forEach((tupleElement) -> {
                Object value = tuple.get(tupleElement.getAlias());
                temp.put(tupleElement.getAlias().toLowerCase(), value);
            });
            ObjectMapper map = new ObjectMapper();
            map.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);
            map.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

            try {
                String mapToString = map.writeValueAsString(temp);
                arrayList.add(map.readValue(mapToString, dtoClass));
            } catch (JsonProcessingException var6) {
                throw new RuntimeException(var6.getMessage());
            }
        });
        return arrayList;
    }

    public static Object getValueParamsFromObject(Object object, String strNameParam) {
        Class clazz = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        Field[] var4 = fields;
        int var5 = fields.length;

        for(int var6 = 0; var6 < var5; ++var6) {
            Field field = var4[var6];

            try {
                field.setAccessible(true);
                String name = field.getName().trim().toLowerCase();
                if (name.equals(strNameParam.trim().toLowerCase())) {
                    return field.get(object);
                }
            } catch (IllegalAccessException | IllegalArgumentException var10) {
                LOGGER.error(var10);
            }
        }

        return null;
    }
    public static List<String> getListParamsSql(String strSql) {
        if (strSql != null && strSql.trim().length() > 0) {
            List<String> listParameter = new ArrayList();
            String strSqlSub = strSql.toLowerCase().replaceAll("\\s+", " ");

            while(true) {
                int iFirstParasm = strSqlSub.indexOf(58);
                if (iFirstParasm <= 0) {
                    return listParameter;
                }

                int iLast = -1;
                String strVariable = null;
                int length = strSqlSub.length();

                for(int i = iFirstParasm; i < length; ++i) {
                    String strParams;
                    if (' ' == strSqlSub.charAt(i) || ',' == strSqlSub.charAt(i) || ')' == strSqlSub.charAt(i)) {
                        strVariable = strSqlSub.substring(iFirstParasm, i);
                        strParams = strVariable.replace(":", "").trim();
                        listParameter.add(strParams);
                        iLast = i;
                        break;
                    }

                    if (i == strSqlSub.length() - 1) {
                        strVariable = strSqlSub.substring(iFirstParasm);
                        strParams = strVariable.replace(":", "").trim();
                        listParameter.add(strParams);
                        iLast = strSqlSub.length();
                        break;
                    }
                }

                if (iLast > 0) {
                    strSqlSub = strSqlSub.replace(strVariable, "");
                }
            }
        } else {
            return null;
        }
    }

    public static Properties readProperties(String filename) {
        try {
            Properties properties = new Properties();
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            InputStream stream = loader.getResourceAsStream(filename);
            properties.load(stream);
            return properties;
        } catch (IOException var4) {
            LOGGER.error("readProperties not file name = " + filename);
            LOGGER.error(var4);
            return null;
        }
    }
    public static String removeAccent(String s) {
        if (s == null) {
            return "";
        } else {
            String temp = Normalizer.normalize(s, Normalizer.Form.NFD);
            Pattern pattern = Pattern.compile("\\p{InCombiningDiacriticalMarks}+");
            return pattern.matcher(temp).replaceAll("").replace("đ", "d").replace("Đ", "D");
        }
    }

    public static String generateUUId() {
        UUID uuid = UUID.randomUUID();
        return uuid.toString();
    }

    public static String generateQuestionMark(int size) {
        if (size == 0) {
            return "";
        } else {
            StringBuilder questionMarks = new StringBuilder();

            for(int i = 0; i < size - 1; ++i) {
                questionMarks.append(" ?, ");
            }

            questionMarks.append(" ? ");
            return questionMarks.toString();
        }
    }

    public static String bytesToHex(byte[] bytes) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[bytes.length * 2];

        for(int j = 0; j < bytes.length; ++j) {
            int v = bytes[j] & 255;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 15];
        }

        return new String(hexChars);
    }

    public static String createTokenRandom() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[20];
        random.nextBytes(bytes);
        return Arrays.toString(bytes);
    }

    public static byte[] hexStringToByteArray(String s) {
        int len = s.length();
        byte[] data = new byte[len / 2];

        for(int i = 0; i < len; i += 2) {
            data[i / 2] = (byte)((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i + 1), 16));
        }

        return data;
    }

    public static Timestamp convertDateToSql(Date date) {
        Timestamp result = null;

        try {
            result = new Timestamp(date.getTime());
        } catch (Exception var3) {
            LOGGER.error("Loi! FuctionCommon.convertDateToSql: " + var3);
        }

        return result;
    }

    public static String trimStringSpace(String str) {
        str = str.replaceAll("\\s+", " ");
        str = str.replaceAll("(^\\s+|\\s+$)", "");
        return str;
    }

    public static boolean hasColumn(ResultSet rs, String columnName) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        int columns = rsmd.getColumnCount();

        for(int x = 1; x <= columns; ++x) {
            String cl = rsmd.getColumnName(x);
            if (columnName.toLowerCase().equals(cl.toLowerCase())) {
                return true;
            }
        }

        return false;
    }

    public static String convertObjectToStringJson(Object object) {
        String result= "";
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            result = objectMapper.writeValueAsString(objectMapper);
        } catch (Exception var4) {
            LOGGER.error("Loi convertObjectToStringJson ", var4);
        }
        return result;
    }
}
