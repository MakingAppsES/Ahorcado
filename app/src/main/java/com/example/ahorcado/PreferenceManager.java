package com.example.ahorcado;

import android.app.Activity;
import android.content.SharedPreferences;

/**
 * Clase que encapsula el funcionamiento de <tt>SharedPreferences</tt> de manera
 * que su uso sea mucho m&aacute;s sencillo.
 */
public class PreferenceManager {
    /**
     * Crea un SharedPreferences correspondiente a las preferencias
     * por defecto de la actividad que se pasa.
     *
     * @param activity Actividad de la que queremos las preferencias
     * @return SharedPreferences de la actividad
     */
    public static SharedPreferences getSharedPreferences(Activity activity) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(activity);
    }

    /**
     * Devuelve un editor de SharedPreferences correspondiente a las preferencias
     * por defecto de la actividad que se pasa.
     *
     * @param activity Actividad de la que queremos el editor de preferencias
     * @return Editor de preferencias
     */
    public static SharedPreferences.Editor getEditor(Activity activity) {
        return android.preference.PreferenceManager.getDefaultSharedPreferences(activity).edit();
    }

    /**
     * Devuelve el <tt>String</tt>, correspondiente con la clave que se pasa, de las preferencias
     * por defecto de la actividad que se pasa como par&aacute;metro.
     *
     * @param key Clave para extraer el <tt>String</tt>
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>String</tt> correspondiente con la clave o <tt>null</tt> si no existe esa clave
     */
    public static String getString(String key, Activity activity) {
        return getSharedPreferences(activity).getString(key, null);
    }

    /**
     * Devuelve el <tt>int</tt>, correspondiente con la clave que se pasa, de las preferencias
     * por defecto de la actividad que se pasa como par&aacute;metro.
     *
     * @param key Clave para extraer el <tt>int</tt>
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>int</tt> correspondiente con la clave
     * @throws Exception Si la clave no existe en las preferencias
     */
    public static int getInt(String key, Activity activity) throws Exception {
        if(getSharedPreferences(activity).contains(key))
            return getSharedPreferences(activity).getInt(key, -1);
        else
            throw new Exception("Key not in preferences");
    }

    /**
     * Devuelve el <tt>boolean</tt>, correspondiente con la clave que se pasa, de las preferencias
     * por defecto de la actividad que se pasa como par&aacute;metro.
     *
     * @param key Clave para extraer el <tt>boolean</tt>
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>boolean</tt> correspondiente con la clave
     * @throws Exception Si la clave no existe en las preferencias
     */
    public static boolean getBoolean(String key, Activity activity) throws Exception {
        if(getSharedPreferences(activity).contains(key))
            return getSharedPreferences(activity).getBoolean(key, false);
        else
            throw new Exception("Key not in preferences");
    }

    /**
     * Devuelve el <tt>float</tt>, correspondiente con la clave que se pasa, de las preferencias
     * por defecto de la actividad que se pasa como par&aacute;metro.
     *
     * @param key Clave para extraer el <tt>float</tt>
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>float</tt> correspondiente con la clave
     * @throws Exception Si la clave no existe en las preferencias
     */
    public static float getFloat(String key, Activity activity) throws Exception {
        if(getSharedPreferences(activity).contains(key))
            return getSharedPreferences(activity).getFloat(key, 0.0f);
        else
            throw new Exception("Key not in preferences");
    }

    /**
     * Devuelve el <tt>long</tt>, correspondiente con la clave que se pasa, de las preferencias
     * por defecto de la actividad que se pasa como par&aacute;metro.
     *
     * @param key Clave para extraer el <tt>long</tt>
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>long</tt> correspondiente con la clave
     * @throws Exception Si la clave no existe en las preferencias
     */
    public static long getLong(String key, Activity activity) throws Exception {
        if(getSharedPreferences(activity).contains(key))
            return getSharedPreferences(activity).getLong(key, -1);
        else
            throw new Exception("Key not in preferences");
    }

    /**
     * Asigna un <tt>boolean</tt> a una clave en las preferencias por defecto de una actividad.
     *
     * @param key Clave con la que almacenaremos el valor
     * @param value Valor booleano que queramos asignar a la clave
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>true</tt> de se han guardado los cambios y <tt>false</tt> en caso contrario
     */
    public static boolean putBoolean(String key, Boolean value, Activity activity) {
        SharedPreferences.Editor ed = getEditor(activity);
        ed.putBoolean(key,value);
        return ed.commit();
    }

    /**
     * Asigna un <tt>float</tt> a una clave en las preferencias por defecto de una actividad.
     *
     * @param key Clave con la que almacenaremos el valor
     * @param value Valor que queramos asignar a la clave
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>true</tt> de se han guardado los cambios y <tt>false</tt> en caso contrario
     */
    public static boolean putFloat(String key, float value, Activity activity) {
        SharedPreferences.Editor ed = getEditor(activity);
        ed.putFloat(key,value);
        return ed.commit();
    }

    /**
     * Asigna un <tt>int</tt> a una clave en las preferencias por defecto de una actividad.
     *
     * @param key Clave con la que almacenaremos el valor
     * @param value Valor que queramos asignar a la clave
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>true</tt> de se han guardado los cambios y <tt>false</tt> en caso contrario
     */
    public static boolean putInt(String key, int value, Activity activity) {
        SharedPreferences.Editor ed = getEditor(activity);
        ed.putInt(key,value);
        return ed.commit();
    }

    /**
     * Asigna un <tt>long</tt> a una clave en las preferencias por defecto de una actividad.
     *
     * @param key Clave con la que almacenaremos el valor
     * @param value Valor que queramos asignar a la clave
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>true</tt> de se han guardado los cambios y <tt>false</tt> en caso contrario
     */
    public static boolean putLong(String key, long value, Activity activity) {
        SharedPreferences.Editor ed = getEditor(activity);
        ed.putLong(key,value);
        return ed.commit();
    }

    /**
     * Asigna un <tt>String</tt> a una clave en las preferencias por defecto de una actividad.
     *
     * @param key Clave con la que almacenaremos el valor
     * @param value Valor que queramos asignar a la clave
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>true</tt> de se han guardado los cambios y <tt>false</tt> en caso contrario
     */
    public static boolean putString(String key, String value, Activity activity) {
        SharedPreferences.Editor ed = getEditor(activity);
        ed.putString(key,value);
        return ed.commit();
    }

    /**
     * Elimina el valor asignado a una clave en las preferencias por defecto de una actividad.
     *
     * @param key Clave que queremos eliminar
     * @param activity Actividad de la que queremos las preferencias
     * @return <tt>true</tt> de se han guardado los cambios y <tt>false</tt> en caso contrario
     */
    public static boolean remove(String key, Activity activity) {
        SharedPreferences.Editor ed = getEditor(activity);
        ed.remove(key);
        return ed.commit();
    }
}
