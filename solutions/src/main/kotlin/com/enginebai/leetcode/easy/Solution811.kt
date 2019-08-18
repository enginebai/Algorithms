package com.enginebai.leetcode.easy

object Solution811 {
    fun subdomainVisits(cpdomains: Array<String>): List<String> {
        val map = mutableMapOf<String, Int>()
        cpdomains.forEach { pair ->
            val pairSplit = pair.split("\\s+".toRegex())
            if (pairSplit.size == 2) {
                val domain = pairSplit[1]
                val count = pairSplit[0].toInt()
                map.addDomainCount(domain, count)

                var subdomain = domain.substring(domain.indexOf(".") + 1)
                while (subdomain.isNotEmpty()) {
                    map.addDomainCount(subdomain, count)
                    if (subdomain.contains(".")) {
                        subdomain = subdomain.substring(subdomain.indexOf(".") + 1)
                    } else {
                        break
                    }
                }
            }
        }

        val list = mutableListOf<String>()
        for ((key,value) in map.entries) {
            list.add("$value $key")
        }
        return list
    }

    private fun MutableMap<String, Int>.addDomainCount(domain: String, count: Int) {
        if (this.containsKey(domain))
            this[domain] = this[domain]?.plus(count) ?: 0
        else
            this[domain] = count
    }
}

fun main() {
    val input1 = arrayOf("9001 discuss.leetcode.com")
    val input2 = arrayOf("900 google.mail.com", "50 yahoo.com", "1 intel.mail.com", "5 wiki.org")

    println(Solution811.subdomainVisits(input1))
    println(Solution811.subdomainVisits(input2))
}