package com.sadig.exampleapp.data

class FakeDataSource {
    companion object {
        fun getFakeData(): HashMap<String, List<String>> {
            val map = HashMap<String, List<String>>()

            return map.apply {
                this["name"] = listOf("sadig", "jack", "ivan")
                this["age"] = listOf("22", "23", "19")
                this["hometown"] = listOf("Baku", "Berlin", "Moscow")
            }
        }

        fun getFakeDataAsString(): String {
            val sb = StringBuilder()
            for ((key, value ) in getFakeData()) {
                sb.append("$key: ${value.joinToString()} \n")
            }
            return sb.toString()
        }
    }
}