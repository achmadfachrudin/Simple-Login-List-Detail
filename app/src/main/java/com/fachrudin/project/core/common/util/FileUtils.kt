package com.fachrudin.project.core.common.util

import java.io.*
import java.net.URLConnection

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class FileUtils {

    companion object {
        /**
         * Write bytes from stream to a file.
         *
         * @param[input] InputStream to read from
         * @param[output] Destination file
         * @return true if success, false otherwise
         */
        @JvmStatic
        fun writeStreamToFile(input: InputStream, output: File): Boolean {
            try {
                val out = FileOutputStream(output)
                val buf = ByteArray(1024)
                var len: Int = 0
                while ({ len = input.read(buf); len }() > 0) {
                    out.write(buf, 0, len)
                }
                input.close()
                out.close()
                return true
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return false
        }

        /**
         * Copy file to other location.
         *
         * @param[source] File to copy
         * @param[destination] Destination file
         * @return true if success, false otherwise
         */
        @JvmStatic
        fun copy(source: File, destination: File): Boolean {
            try {
                val fin = FileInputStream(source)
                val fout = FileOutputStream(destination)
                val buf = ByteArray(1024)
                var len: Int = 0
                while ({ len = fin.read(buf); len }() > 0) {
                    fout.write(buf, 0, len)
                }
                fin.close()
                fout.close()
                return true
            } catch (e: FileNotFoundException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return false
        }

        /**
         * Delete a file.
         *
         * @param[fileName] File path
         * @return true if success, false otherwise
         */
        @JvmStatic
        fun delete(fileName: String): Boolean {
            val file = File(fileName)
            return file.delete()
        }

        /**
         * Check if a file path is exist.
         *
         * @param[fileName] File path
         * @return true if file exist, false otherwise
         */
        @JvmStatic
        fun isExist(fileName: String): Boolean {
            return File(fileName).exists()
        }

        /**
         * Create a directory is not exist.
         *
         * @param[path] Directory path to create
         */
        @JvmStatic
        fun createDirsIfNotExist(path: String) {
            val file = File(path)
            if (!file.exists()) {
                file.mkdirs()
            }
        }

        /**
         * Get mime-type of a file based on it's extension
         *
         * @param[file] Input file to check
         * @return Mime-type of input file, null if error
         */
        @JvmStatic
        fun getMimeType(file: File): String? {
            return URLConnection.guessContentTypeFromName(file.getName())
        }
    }
}
