package com.geno1024.ns.htmlTags

import org.jsoup.Jsoup

object GetAttributes
{
    @JvmStatic
    fun main(args: Array<String>)
    {
        println(Jsoup.connect("https://developer.mozilla.org/en-US/docs/Web/HTML/Global_attributes")
            .get()
            .body()
            .select("table.compat-table")
            .first()
            .select("tbody")
            .select("code")
            .joinToString(separator = ",\n", transform = {"\"${it.text()}\""})
        )
    }
}
