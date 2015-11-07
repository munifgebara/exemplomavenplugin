/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.agilemavenplugin;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import org.apache.maven.project.MavenProject;

/**
 *
 * @author munif
 */
public class PluginsUtil {

    public static void assina() {
        System.out.println(""
                + "             _ _      \n"
                + "            (_) |     \n"
                + "  __ _  __ _ _| | ___ \n"
                + " / _` |/ _` | | |/ _ \\\n"
                + "| (_| | (_| | | |  __/\n"
                + " \\__,_|\\__, |_|_|\\___|\n"
                + "        __/ |         \n"
                + "       |___/          "
                + "");
    }

    public static String windowsSafe(String s) {
        return s.replaceAll("\\\\", "/");
    }

    public static ClassLoader getClassLoader(MavenProject project) {
        ClassLoader aRetornar = null;
        try {
            List elementos = new ArrayList();
            elementos.addAll(project.getRuntimeClasspathElements());
            elementos.addAll(project.getTestClasspathElements());

            URL[] runtimeUrls = new URL[elementos.size()];
            for (int i = 0; i < elementos.size(); i++) {
                String element = (String) elementos.get(i);
                runtimeUrls[i] = new File(element).toURI().toURL();
            }
            aRetornar = new URLClassLoader(runtimeUrls,
                    Thread.currentThread().getContextClassLoader());

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return aRetornar;
    }

}
