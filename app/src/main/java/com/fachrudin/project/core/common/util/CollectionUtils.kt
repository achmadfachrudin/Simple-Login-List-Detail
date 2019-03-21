package com.fachrudin.project.core.common.util

/**
 * @author achmad.fachrudin
 * @date 21-Nov-18
 */
class CollectionUtils {

    companion object {
        /**
         * Creates a map from key value pairs of arguments. The parameters should be pairs of key values.
         * eg: the following creates a map mapping "key1" to "value1" and "key2" to "value2"
         *
         * @sample mapOf
         * Map<String, Object> map = CollectionUtils.mapOf("key1", "value1", "key2", "value2")
         *
         * @param[list] Array of arguments. must be in order of key(n) -> value(n)
         * @return Map data key value pairs
         * @throws IllegalArgumentException if args is empty or odd sized
         */
        @JvmStatic
        fun mapOf(vararg list: Any): Map<Any, Any> {
            if (list.isNotEmpty() && list.size % 2 == 0) {
                val result = HashMap<Any, Any>()
                for (i in 0..(list.size - 1) step 2) {
                    result.put(list.get(i), list.get(i + 1))
                }
                return result
            }
            throw IllegalArgumentException("args length must be greater than 0 and even sized");
        }
    }
}
