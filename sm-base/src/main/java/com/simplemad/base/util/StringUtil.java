/*
 * File   : $Source: /usr/local/cvs/opencms/src/org/opencms/util/CmsStringUtil.java,v $
 * Date   : $Date: 2008-06-20 15:38:03 $
 * Version: $Revision: 1.49 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (c) 2002 - 2008 Alkacon Software GmbH (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software GmbH, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package com.simplemad.base.util;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;


/**
 * Provides String utility functions.<p>
 * 
 * @author  Andreas Zahner 
 * @author  Alexander Kandzior 
 * @author Thomas Weckert  
 * 
 * @version $Revision: 1.49 $ 
 * 
 * @since 6.0.0 
 */
public final class StringUtil extends StringUtils{

    /** Regular expression that matches the HTML body end tag. */
    public static final String BODY_END_REGEX = "<\\s*/\\s*body[^>]*>";

    /** Regular expression that matches the HTML body start tag. */
    public static final String BODY_START_REGEX = "<\\s*body[^>]*>";

    /** Constant for <code>"false"</code>. */
    public static final String FALSE = Boolean.toString(false);

    /** a convenient shorthand to the line separator constant. */
    public static final String LINE_SEPARATOR = System.getProperty("line.separator");

    /** Context macro. */
    public static final String MACRO_OPENCMS_CONTEXT = "${OpenCmsContext}";

    /** Contains all chars that end a sentence in the {@link #trimToSize(String, int, int, String)} method. */
    public static final char[] SENTENCE_ENDING_CHARS = {'.', '!', '?'};

    /** a convenient shorthand for tabulations.  */
    public static final String TABULATOR = "  ";

    /** Constant for <code>"true"</code>. */
    public static final String TRUE = Boolean.toString(true);

    /** Regex pattern that matches an end body tag. */
    private static final Pattern BODY_END_PATTERN = Pattern.compile(BODY_END_REGEX, Pattern.CASE_INSENSITIVE);

    /** Regex pattern that matches a start body tag. */
    private static final Pattern BODY_START_PATTERN = Pattern.compile(BODY_START_REGEX, Pattern.CASE_INSENSITIVE);

    /** Day constant. */
    private static final long DAYS = 1000 * 60 * 60 * 24;

    /** Hour constant. */
    private static final long HOURS = 1000 * 60 * 60;

    /** OpenCms context replace String, static for performance reasons. */
    @SuppressWarnings("unused")
	private static String m_contextReplace;

    /** OpenCms context search String, static for performance reasons. */
    @SuppressWarnings("unused")
	private static String m_contextSearch;

    /** Minute constant. */
    private static final long MINUTES = 1000 * 60;

    /** Second constant. */
    private static final long SECONDS = 1000;

    /** Regex that matches an encoding String in an xml head. */
    private static final Pattern XML_ENCODING_REGEX = Pattern.compile(
        "encoding\\s*=\\s*[\"'].+[\"']",
        Pattern.CASE_INSENSITIVE);

    /** Regex that matches an xml head. */
    private static final Pattern XML_HEAD_REGEX = Pattern.compile("<\\s*\\?.*\\?\\s*>", Pattern.CASE_INSENSITIVE);

    /** 
     * Default constructor (empty), private because this class has only 
     * static methods.<p>
     */
    private StringUtil() {

        // empty
    }

    /**
     * Changes the filename suffix. 
     * 
     * @param filename the filename to be changed
     * @param suffix the new suffix of the file
     * 
     * @return the filename with the replaced suffix
     */
    public static String changeFileNameSuffixTo(String filename, String suffix) {

        int dotPos = filename.lastIndexOf('.');
        if (dotPos != -1) {
            return filename.substring(0, dotPos + 1) + suffix;
        } else {
            // the string has no suffix
            return filename;
        }
    }

   
    /**
     * Returns a string representation for the given collection using the given separator.<p>
     * 
     * @param collection the collection to print
     * @param separator the item separator
     * 
     * @return the string representation for the given collection
     */
    @SuppressWarnings("rawtypes")
	public static String collectionAsString(Collection collection, String separator) {

        StringBuffer string = new StringBuffer(128);
        Iterator it = collection.iterator();
        while (it.hasNext()) {
            string.append(it.next());
            if (it.hasNext()) {
                string.append(separator);
            }
        }
        return string.toString();
    }

   
    /**
     * Escapes a String so it may be used in JavaScript String definitions.<p>
     * 
     * This method replaces line breaks, quotation marks and \ characters.<p>
     * 
     * @param source the String to escape
     * 
     * @return the escaped String
     */
    public static String escapeJavaScript(String source) {

        source = StringUtil.substitute(source, "\\", "\\\\");
        source = StringUtil.substitute(source, "\"", "\\\"");
        source = StringUtil.substitute(source, "\'", "\\\'");
        source = StringUtil.substitute(source, "\r\n", "\\n");
        source = StringUtil.substitute(source, "\n", "\\n");
        return source;
    }

    /**
     * Escapes a String so it may be used as a Perl5 regular expression.<p>
     * 
     * This method replaces the following characters in a String:<br>
     * <code>{}[]()\$^.*+/</code><p>
     * 
     * @param source the string to escape
     * 
     * @return the escaped string
     */
    public static String escapePattern(String source) {

        if (source == null) {
            return null;
        }
        StringBuffer result = new StringBuffer(source.length() * 2);
        for (int i = 0; i < source.length(); ++i) {
            char ch = source.charAt(i);
            switch (ch) {
                case '\\':
                    result.append("\\\\");
                    break;
                case '/':
                    result.append("\\/");
                    break;
                case '$':
                    result.append("\\$");
                    break;
                case '^':
                    result.append("\\^");
                    break;
                case '.':
                    result.append("\\.");
                    break;
                case '*':
                    result.append("\\*");
                    break;
                case '+':
                    result.append("\\+");
                    break;
                case '|':
                    result.append("\\|");
                    break;
                case '?':
                    result.append("\\?");
                    break;
                case '{':
                    result.append("\\{");
                    break;
                case '}':
                    result.append("\\}");
                    break;
                case '[':
                    result.append("\\[");
                    break;
                case ']':
                    result.append("\\]");
                    break;
                case '(':
                    result.append("\\(");
                    break;
                case ')':
                    result.append("\\)");
                    break;
                default:
                    result.append(ch);
            }
        }
        return new String(result);
    }

    /**
     * This method takes a part of a html tag definition, an attribute to extend within the
     * given text and a default value for this attribute; and returns a <code>{@link Map}</code>
     * with 2 values: a <code>{@link String}</code> with key <code>"text"</code> with the new text
     * without the given attribute, and another <code>{@link String}</code> with key <code>"value"</code>
     * with the new extended value for the given attribute, this value is surrounded by the same type of 
     * quotation marks as in the given text.<p> 
     * 
     * @param text the text to search in
     * @param attribute the attribute to remove and extend from the text
     * @param defValue a default value for the attribute, should not have any quotation mark
     * 
     * @return a map with the new text and the new value for the given attribute 
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map extendAttribute(String text, String attribute, String defValue) {

        Map retValue = new HashMap();
        retValue.put("text", text);
        retValue.put("value", "'" + defValue + "'");
        if ((text != null) && (text.toLowerCase().indexOf(attribute.toLowerCase()) >= 0)) {
            // this does not work for things like "att=method()" without quotations.
            String quotation = "\'";
            int pos1 = text.toLowerCase().indexOf(attribute.toLowerCase());
            // looking for the opening quotation mark
            int pos2 = text.indexOf(quotation, pos1);
            int test = text.indexOf("\"", pos1);
            if ((test > -1) && ((pos2 == -1) || (test < pos2))) {
                quotation = "\"";
                pos2 = test;
            }
            // assuming there is a closing quotation mark
            int pos3 = text.indexOf(quotation, pos2 + 1);
            // building the new attribute value
            String newValue = quotation + defValue + text.substring(pos2 + 1, pos3 + 1);
            // removing the onload statement from the parameters
            String newText = text.substring(0, pos1);
            if (pos3 < text.length()) {
                newText += text.substring(pos3 + 1);
            }
            retValue.put("text", newText);
            retValue.put("value", newValue);
        }
        return retValue;
    }

    /**
     * Extracts the content of a <code>&lt;body&gt;</code> tag in a HTML page.<p>
     * 
     * This method should be pretty robust and work even if the input HTML does not contains
     * a valid body tag.<p> 
     * 
     * @param content the content to extract the body from
     * 
     * @return the extracted body tag content
     */
    public static String extractHtmlBody(String content) {

        Matcher startMatcher = BODY_START_PATTERN.matcher(content);
        Matcher endMatcher = BODY_END_PATTERN.matcher(content);

        int start = 0;
        int end = content.length();

        if (startMatcher.find()) {
            start = startMatcher.end();
        }

        if (endMatcher.find(start)) {
            end = endMatcher.start();
        }

        return content.substring(start, end);
    }

    /**
     * Extracts the xml encoding setting from an xml file that is contained in a String by parsing 
     * the xml head.<p>
     * 
     * This is useful if you have a byte array that contains a xml String, 
     * but you do not know the xml encoding setting. Since the encoding setting 
     * in the xml head is usually encoded with standard US-ASCII, you usually
     * just create a String of the byte array without encoding setting,
     * and use this method to find the 'true' encoding. Then create a String
     * of the byte array again, this time using the found encoding.<p>   
     * 
     * This method will return <code>null</code> in case no xml head 
     * or encoding information is contained in the input.<p>
     * 
     * @param content the xml content to extract the encoding from
     * 
     * @return the extracted encoding, or null if no xml encoding setting was found in the input 
     */
    public static String extractXmlEncoding(String content) {

        String result = null;
        Matcher xmlHeadMatcher = XML_HEAD_REGEX.matcher(content);
        if (xmlHeadMatcher.find()) {
            String xmlHead = xmlHeadMatcher.group();
            Matcher encodingMatcher = XML_ENCODING_REGEX.matcher(xmlHead);
            if (encodingMatcher.find()) {
                String encoding = encodingMatcher.group();
                int pos1 = encoding.indexOf('=') + 2;
                String charset = encoding.substring(pos1, encoding.length() - 1);
                if (Charset.isSupported(charset)) {
                    result = charset;
                }
            }
        }
        return result;
    }

    /**
     * Formats a resource name that it is displayed with the maximum length and path information is adjusted.<p>
     * In order to reduce the length of the displayed names, single folder names are removed/replaced with ... successively, 
     * starting with the second! folder. The first folder is removed as last.<p>
     * 
     * Example: formatResourceName("/myfolder/subfolder/index.html", 21) returns <code>/myfolder/.../index.html</code>.<p>
     * 
     * @param name the resource name to format
     * @param maxLength the maximum length of the resource name (without leading <code>/...</code>)
     * 
     * @return the formatted resource name
     */
    public static String formatResourceName(String name, int maxLength) {

        if (name == null) {
            return null;
        }

        if (name.length() <= maxLength) {
            return name;
        }

        int total = name.length();
        String[] names = StringUtil.splitAsArray(name, "/");
        if (name.endsWith("/")) {
            names[names.length - 1] = names[names.length - 1] + "/";
        }
        for (int i = 1; (total > maxLength) && (i < names.length - 1); i++) {
            if (i > 1) {
                names[i - 1] = "";
            }
            names[i] = "...";
            total = 0;
            for (int j = 0; j < names.length; j++) {
                int l = names[j].length();
                total += l + ((l > 0) ? 1 : 0);
            }
        }
        if (total > maxLength) {
            names[0] = (names.length > 2) ? "" : (names.length > 1) ? "..." : names[0];
        }

        StringBuffer result = new StringBuffer();
        for (int i = 0; i < names.length; i++) {
            if (names[i].length() > 0) {
                result.append("/");
                result.append(names[i]);
            }
        }

        return result.toString();
    }

    /**
     * Formats a runtime in the format hh:mm:ss, to be used e.g. in reports.<p>
     * 
     * If the runtime is greater then 24 hours, the format dd:hh:mm:ss is used.<p> 
     * 
     * @param runtime the time to format
     * 
     * @return the formatted runtime
     */
    public static String formatRuntime(long runtime) {

        long seconds = (runtime / SECONDS) % 60;
        long minutes = (runtime / MINUTES) % 60;
        long hours = (runtime / HOURS) % 24;
        long days = runtime / DAYS;
        StringBuffer strBuf = new StringBuffer();

        if (days > 0) {
            if (days < 10) {
                strBuf.append('0');
            }
            strBuf.append(days);
            strBuf.append(':');
        }

        if (hours < 10) {
            strBuf.append('0');
        }
        strBuf.append(hours);
        strBuf.append(':');

        if (minutes < 10) {
            strBuf.append('0');
        }
        strBuf.append(minutes);
        strBuf.append(':');

        if (seconds < 10) {
            strBuf.append('0');
        }
        strBuf.append(seconds);

        return strBuf.toString();
    }

  
    /**
     * Returns <code>true</code> if the provided String is either <code>null</code>
     * or the empty String <code>""</code>.<p> 
     * 
     * @param value the value to check
     * 
     * @return true, if the provided value is null or the empty String, false otherwise
     */
    public static boolean isEmpty(String value) {

        return (value == null) || (value.length() == 0);
    }

    /**
     * Returns <code>true</code> if the provided String is either <code>null</code>
     * or contains only white spaces.<p> 
     * 
     * @param value the value to check
     * 
     * @return true, if the provided value is null or contains only white spaces, false otherwise
     */
    public static boolean isEmptyOrWhitespaceOnly(String value) {

        return isEmpty(value) || (value.trim().length() == 0);
    }

    /**
     * Returns <code>true</code> if the provided Objects are either both <code>null</code> 
     * or equal according to {@link Object#equals(Object)}.<p>
     * 
     * @param value1 the first object to compare
     * @param value2 the second object to compare
     * 
     * @return <code>true</code> if the provided Objects are either both <code>null</code> 
     *              or equal according to {@link Object#equals(Object)} 
     */
    public static boolean isEqual(Object value1, Object value2) {

        if (value1 == null) {
            return (value2 == null);
        }
        return value1.equals(value2);
    }

    /**
     * Returns <code>true</code> if the provided String is neither <code>null</code>
     * nor the empty String <code>""</code>.<p> 
     * 
     * @param value the value to check
     * 
     * @return true, if the provided value is not null and not the empty String, false otherwise
     */
    public static boolean isNotEmpty(String value) {

        return (value != null) && (value.length() != 0);
    }

    /**
     * Returns <code>true</code> if the provided String is neither <code>null</code>
     * nor contains only white spaces.<p> 
     * 
     * @param value the value to check
     * 
     * @return <code>true</code>, if the provided value is <code>null</code> 
     *          or contains only white spaces, <code>false</code> otherwise
     */
    public static boolean isNotEmptyOrWhitespaceOnly(String value) {

        return (value != null) && (value.trim().length() > 0);
    }

    /**
     * Checks if the given class name is a valid Java class name.<p>
     * 
     * @param className the name to check
     * 
     * @return true if the given class name is a valid Java class name
     */
    public static boolean isValidJavaClassName(String className) {

        if (StringUtil.isEmpty(className)) {
            return false;
        }
        int length = className.length();
        boolean nodot = true;
        for (int i = 0; i < length; i++) {
            char ch = className.charAt(i);
            if (nodot) {
                if (ch == '.') {
                    return false;
                } else if (Character.isJavaIdentifierStart(ch)) {
                    nodot = false;
                } else {
                    return false;
                }
            } else {
                if (ch == '.') {
                    nodot = true;
                } else if (Character.isJavaIdentifierPart(ch)) {
                    nodot = false;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns the last index of any of the given chars in the given source.<p> 
     * 
     * If no char is found, -1 is returned.<p>
     * 
     * @param source the source to check
     * @param chars the chars to find
     * 
     * @return the last index of any of the given chars in the given source, or -1
     */
    public static int lastIndexOf(String source, char[] chars) {

        // now try to find an "sentence ending" char in the text in the "findPointArea"
        int result = -1;
        for (int i = 0; i < chars.length; i++) {
            int pos = source.lastIndexOf(chars[i]);
            if (pos > result) {
                // found new last char
                result = pos;
            }
        }
        return result;
    }

    /**
     * Returns the last index a whitespace char the given source.<p> 
     * 
     * If no whitespace char is found, -1 is returned.<p>
     * 
     * @param source the source to check
     * 
     * @return the last index a whitespace char the given source, or -1
     */
    public static int lastWhitespaceIn(String source) {

        if (StringUtil.isEmpty(source)) {
            return -1;
        }
        int pos = -1;
        for (int i = source.length() - 1; i >= 0; i--) {
            if (Character.isWhitespace(source.charAt(i))) {
                pos = i;
                break;
            }
        }
        return pos;
    }

    /**
     * Returns a string representation for the given map using the given separators.<p>
     * 
     * @param map the map to write
     * @param sepItem the item separator string
     * @param sepKeyval the key-value pair separator string
     * 
     * @return the string representation for the given map
     */
    @SuppressWarnings("rawtypes")
	public static String mapAsString(Map map, String sepItem, String sepKeyval) {

        StringBuffer string = new StringBuffer(128);
        Iterator it = map.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            string.append(entry.getKey());
            string.append(sepKeyval);
            string.append(entry.getValue());
            if (it.hasNext()) {
                string.append(sepItem);
            }
        }
        return string.toString();
    }



    /**
     * Splits a String into substrings along the provided char delimiter and returns
     * the result as an Array of Substrings.<p>
     *
     * @param source the String to split
     * @param delimiter the delimiter to split at
     *
     * @return the Array of splitted Substrings
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] splitAsArray(String source, char delimiter) {

        List result = splitAsList(source, delimiter);
        return (String[])result.toArray(new String[result.size()]);
    }

    /**
     * Splits a String into substrings along the provided String delimiter and returns
     * the result as an Array of Substrings.<p>
     *
     * @param source the String to split
     * @param delimiter the delimiter to split at
     *
     * @return the Array of splitted Substrings
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] splitAsArray(String source, String delimiter) {

        List result = splitAsList(source, delimiter);
        return (String[])result.toArray(new String[result.size()]);
    }
    
    /**
     * Splits a String into substrings along the provided String delimiter and returns
     * the result as an Array of Substrings.<p>
     *
     * @param source the String to split
     * @param delimiter the delimiter to split at
     *
     * @return the Array of splitted and trimmed Substrings
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static String[] splitAndTrimAsArray(String source, String delimiter) {
    	List result = splitAsList(source, delimiter, true);
    	return (String[]) result.toArray(new String[result.size()]);
    }

    /**
     * Splits a String into substrings along the provided char delimiter and returns
     * the result as a List of Substrings.<p>
     *
     * @param source the String to split
     * @param delimiter the delimiter to split at
     *
     * @return the List of splitted Substrings
     */
    @SuppressWarnings("rawtypes")
	public static List splitAsList(String source, char delimiter) {

        return splitAsList(source, delimiter, false);
    }

    /**
     * Splits a String into substrings along the provided char delimiter and returns
     * the result as a List of Substrings.<p>
     *
     * @param source the String to split
     * @param delimiter the delimiter to split at
     * @param trim flag to indicate if leading and trailing white spaces should be omitted
     *
     * @return the List of splitted Substrings
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List splitAsList(String source, char delimiter, boolean trim) {

        List result = new ArrayList();
        int i = 0;
        int l = source.length();
        int n = source.indexOf(delimiter);
        while (n != -1) {
            // zero - length items are not seen as tokens at start or end
            if ((i < n) || ((i > 0) && (i < l))) {
                result.add(trim ? source.substring(i, n).trim() : source.substring(i, n));
            }
            i = n + 1;
            n = source.indexOf(delimiter, i);
        }
        // is there a non - empty String to cut from the tail? 
        if (n < 0) {
            n = source.length();
        }
        if (i < n) {
            result.add(trim ? source.substring(i).trim() : source.substring(i));
        }
        return result;
    }

    /**
     * Splits a String into substrings along the provided String delimiter and returns
     * the result as List of Substrings.<p>
     *
     * @param source the String to split
     * @param delimiter the delimiter to split at
     *
     * @return the Array of splitted Substrings
     */
    @SuppressWarnings("rawtypes")
	public static List splitAsList(String source, String delimiter) {

        return splitAsList(source, delimiter, false);
    }

    /**
     * Splits a String into substrings along the provided String delimiter and returns
     * the result as List of Substrings.<p>
     * 
     * @param source the String to split
     * @param delimiter the delimiter to split at
     * @param trim flag to indicate if leading and trailing white spaces should be omitted
     * 
     * @return the Array of splitted Substrings
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static List splitAsList(String source, String delimiter, boolean trim) {

        int dl = delimiter.length();
        if (dl == 1) {
            // optimize for short strings
            return splitAsList(source, delimiter.charAt(0), trim);
        }

        List result = new ArrayList();
        int i = 0;
        int l = source.length();
        int n = source.indexOf(delimiter);
        while (n != -1) {
            // zero - length items are not seen as tokens at start or end:  ",," is one empty token but not three
            if ((i < n) || ((i > 0) && (i < l))) {
                result.add(trim ? source.substring(i, n).trim() : source.substring(i, n));
            }
            i = n + dl;
            n = source.indexOf(delimiter, i);
        }
        // is there a non - empty String to cut from the tail? 
        if (n < 0) {
            n = source.length();
        }
        if (i < n) {
            result.add(trim ? source.substring(i).trim() : source.substring(i));
        }
        return result;
    }

    /**
     * Splits a String into substrings along the provided <code>paramDelim</code> delimiter,
     * then each substring is treat as a key-value pair delimited by <code>keyValDelim</code>.<p>
     * 
     * @param source the string to split
     * @param paramDelim the string to delimit each key-value pair
     * @param keyValDelim the string to delimit key and value
     * 
     * @return a map of splitted key-value pairs
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map splitAsMap(String source, String paramDelim, String keyValDelim) {

        int keyValLen = keyValDelim.length();
        Map params = new HashMap();
        Iterator itParams = StringUtil.splitAsList(source, paramDelim, true).iterator();
        while (itParams.hasNext()) {
            String param = (String)itParams.next();
            int pos = param.indexOf(keyValDelim);
            String key = param;
            String value = "";
            if (pos > 0) {
                key = param.substring(0, pos);
                if (pos + keyValLen < param.length()) {
                    value = param.substring(pos + keyValLen);
                }
            }
            params.put(key, value);
        }
        return params;
    }

    /**
     * Replaces a set of <code>searchString</code> and <code>replaceString</code> pairs, 
     * given by the <code>substitutions</code> Map parameter.<p>
     * 
     * @param source the string to scan
     * @param substitions the map of substitutions
     * 
     * @return the substituted String
     * 
     * @see #substitute(String, String, String)
     */
    @SuppressWarnings("rawtypes")
	public static String substitute(String source, Map substitions) {

        String result = source;
        Iterator it = substitions.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry)it.next();
            result = substitute(result, (String)entry.getKey(), entry.getValue().toString());
        }
        return result;
    }

    /**
     * Substitutes <code>searchString</code> in the given source String with <code>replaceString</code>.<p>
     * 
     * This is a high-performance implementation which should be used as a replacement for 
     * <code>{@link String#replaceAll(java.lang.String, java.lang.String)}</code> in case no
     * regular expression evaluation is required.<p>
     * 
     * @param source the content which is scanned
     * @param searchString the String which is searched in content
     * @param replaceString the String which replaces <code>searchString</code>
     * 
     * @return the substituted String
     */
    public static String substitute(String source, String searchString, String replaceString) {

        if (source == null) {
            return null;
        }

        if (isEmpty(searchString)) {
            return source;
        }

        if (replaceString == null) {
            replaceString = "";
        }
        int len = source.length();
        int sl = searchString.length();
        int rl = replaceString.length();
        int length;
        if (sl == rl) {
            length = len;
        } else {
            int c = 0;
            int s = 0;
            int e;
            while ((e = source.indexOf(searchString, s)) != -1) {
                c++;
                s = e + sl;
            }
            if (c == 0) {
                return source;
            }
            length = len - (c * (sl - rl));
        }

        int s = 0;
        int e = source.indexOf(searchString, s);
        if (e == -1) {
            return source;
        }
        StringBuffer sb = new StringBuffer(length);
        while (e != -1) {
            sb.append(source.substring(s, e));
            sb.append(replaceString);
            s = e + sl;
            e = source.indexOf(searchString, s);
        }
        e = len;
        sb.append(source.substring(s, e));
        return sb.toString();
    }

  
    /**
     * Returns the java String literal for the given String. <p>
     *  
     * This is the form of the String that had to be written into source code 
     * using the unicode escape sequence for special characters. <p> 
     * 
     * Example: "�" would be transformed to "\\u00C4".<p>
     * 
     * @param s a string that may contain non-ascii characters 
     * 
     * @return the java unicode escaped string Literal of the given input string
     */
    public static String toUnicodeLiteral(String s) {

        StringBuffer result = new StringBuffer();
        char[] carr = s.toCharArray();

        String unicode;
        for (int i = 0; i < carr.length; i++) {
            result.append("\\u");
            // append leading zeros
            unicode = Integer.toHexString(carr[i]).toUpperCase();
            for (int j = 4 - unicode.length(); j > 0; j--) {
                result.append("0");
            }
            result.append(unicode);
        }
        return result.toString();
    }

    /**
     * Returns a substring of the source, which is at most length characters long.<p>
     * 
     * This is the same as calling {@link #trimToSize(String, int, String)} with the 
     * parameters <code>(source, length, " ...")</code>.<p>
     * 
     * @param source the string to trim
     * @param length the maximum length of the string to be returned
     * 
     * @return a substring of the source, which is at most length characters long
     */
    public static String trimToSize(String source, int length) {

        return trimToSize(source, length, length, " ...");
    }

    /**
     * Returns a substring of the source, which is at most length characters long.<p>
     * 
     * If a char is cut, the given <code>suffix</code> is appended to the result.<p>
     * 
     * This is almost the same as calling {@link #trimToSize(String, int, int, String)} with the 
     * parameters <code>(source, length, length*, suffix)</code>. If <code>length</code>
     * if larger then 100, then <code>length* = length / 2</code>,
     * otherwise <code>length* = length</code>.<p>
     * 
     * @param source the string to trim
     * @param length the maximum length of the string to be returned
     * @param suffix the suffix to append in case the String was trimmed
     * 
     * @return a substring of the source, which is at most length characters long
     */
    public static String trimToSize(String source, int length, String suffix) {

        int area = (length > 100) ? length / 2 : length;
        return trimToSize(source, length, area, suffix);
    }

    /**
     * Returns a substring of the source, which is at most length characters long, cut 
     * in the last <code>area</code> chars in the source at a sentence ending char or whitespace.<p>
     * 
     * If a char is cut, the given <code>suffix</code> is appended to the result.<p>
     * 
     * @param source the string to trim
     * @param length the maximum length of the string to be returned
     * @param area the area at the end of the string in which to find a sentence ender or whitespace
     * @param suffix the suffix to append in case the String was trimmed
     * 
     * @return a substring of the source, which is at most length characters long
     */
    public static String trimToSize(String source, int length, int area, String suffix) {

        if ((source == null) || (source.length() <= length)) {
            // no operation is required
            return source;
        }
        if (StringUtil.isEmpty(suffix)) {
            // we need an empty suffix
            suffix = "";
        }
        // must remove the length from the after sequence chars since these are always added in the end
        int modLength = length - suffix.length();
        if (modLength <= 0) {
            // we are to short, return beginning of the suffix
            return suffix.substring(0, length);
        }
        int modArea = area + suffix.length();
        if ((modArea > modLength) || (modArea < 0)) {
            // area must not be longer then max length
            modArea = modLength;
        }

        // first reduce the String to the maximum allowed length
        String findPointSource = source.substring(modLength - modArea, modLength);

        String result;
        // try to find an "sentence ending" char in the text
        int pos = lastIndexOf(findPointSource, SENTENCE_ENDING_CHARS);
        if (pos >= 0) {
            // found a sentence ender in the lookup area, keep the sentence ender
            result = source.substring(0, modLength - modArea + pos + 1) + suffix;
        } else {
            // no sentence ender was found, try to find a whitespace
            pos = lastWhitespaceIn(findPointSource);
            if (pos >= 0) {
                // found a whitespace, don't keep the whitespace
                result = source.substring(0, modLength - modArea + pos) + suffix;
            } else {
                // not even a whitespace was found, just cut away what's to long
                result = source.substring(0, modLength) + suffix;
            }
        }

        return result;
    }

    /**
     * Validates a value against a regular expression.<p>
     * 
     * @param value the value to test
     * @param regex the regular expression
     * @param allowEmpty if an empty value is allowed
     * 
     * @return <code>true</code> if the value satisfies the validation
     */
    public static boolean validateRegex(String value, String regex, boolean allowEmpty) {

        if (StringUtil.isEmptyOrWhitespaceOnly(value)) {
            return allowEmpty;
        }
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(value);
        return matcher.matches();
    }

    /**
     * Checks if the provided name is a valid resource name, that is contains only
     * valid characters.<p>
     *
     * @param name the resource name to check
     * @return true if the resource name is valid, false otherwise 
     * 
     * @deprecated use {@link org.opencms.file.CmsResource#checkResourceName(String)} instead
     */
    public static boolean validateResourceName(String name) {

        if (name == null) {
            return false;
        }
        int l = name.length();
        if (l == 0) {
            return false;
        }
        if (name.length() != name.trim().length()) {
            // leading or trailing white space are not allowed
            return false;
        }
        for (int i = 0; i < l; i++) {
            char ch = name.charAt(i);
            switch (ch) {
                case '/':
                    return false;
                case '\\':
                    return false;
                case ':':
                    return false;
                case '*':
                    return false;
                case '?':
                    return false;
                case '"':
                    return false;
                case '>':
                    return false;
                case '<':
                    return false;
                case '|':
                    return false;
                default:
                    // ISO control chars are not allowed
                    if (Character.isISOControl(ch)) {
                        return false;
                    }
                    // chars not defined in unicode are not allowed
                    if (!Character.isDefined(ch)) {
                        return false;
                    }
            }
        }

        return true;
    }
}