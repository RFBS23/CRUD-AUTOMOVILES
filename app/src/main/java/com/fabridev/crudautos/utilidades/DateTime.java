package com.fabridev.crudautos.utilidades;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class DateTime {
    public static String getDateTimeFormat(String formato){
        //1.- Clase base obtener fecha y hora (Formato ¿?)
        Calendar calendar = Calendar.getInstance();

        //2.- Clase que almacena la informacion proporcionada por el calendario
        Date date = calendar.getTime();
        //3.- Necesitamos Formatear (presentar) el valor DateTime
        SimpleDateFormat simpleDF = new SimpleDateFormat(formato);
        simpleDF.setTimeZone(TimeZone.getTimeZone("America/Lima"));

        //4.- retornar la fecha fomrateado
        return simpleDF.format(date);
    }
}
