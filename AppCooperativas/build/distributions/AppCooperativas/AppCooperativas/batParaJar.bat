@echo off
REM Fichero BAT que generará el JAR

REM Estructura básica de lanzador de JAR sin debug:
REM start “APP JAVA” javaw --module-path lib --add-modules <MODULOS> -jar <NOMBREJAR>

REM Donde:
REM - APP JAVA es el nombre de nuestra app 
REM - MODULOS son los del build.grade del proyecto java:
REM - NOMBREJAR esel nombre del nuestro JAR (debe coincidir con nombre que da en proyecto en la carpeta distributions/bin
REM quité el módulo javafx.media porque yo no lo uso, pero podría hacer falta


REM Lanzador con DEBUG
 java --module-path lib --add-modules javafx.controls,javafx.graphics,javafx.fxml,javafx.swing,javafx.web -jar AppCooperativas.jar

REM Lanzador sin DEBUG
REM start "LIBROS" javaw --module-path lib --add-modules javafx.controls,javafx.graphics,javafx.fxml,javafx.swing,javafx.web -jar crud_javafx_libros_v2.jar

 pause