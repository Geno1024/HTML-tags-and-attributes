package com.geno1024.ns.htmlTags

import org.jsoup.Jsoup

object GetElements
{
    fun getAttribute(tag: String) = Jsoup.connect("https://developer.mozilla.org/en-US/docs/Web/HTML/Element/$tag")
        .get()
        .body()
        .select("article#wikiArticle")
        .select("dl>dt>strong")
        .select("code")
        .joinToString(separator = ",\n", transform = {"\"${it.text()}\""})

    @JvmStatic
    fun main(args: Array<String>)
    {
        println(Jsoup.connect("https://developer.mozilla.org/en-US/docs/Web/HTML/Element")
            .get()
            .body()
            .select("article#wikiArticle")
            .select("table.standard-table")
            .select("tbody>tr")
            .select("td:nth-child(2n+1)")
            .not("strong")
            .map { it.text() }
            .distinct()
            .sorted()
            .joinToString { it.replace(Regex("[<>]"), "")}
            .split(", ")
            .joinToString(separator = ",\n" , transform = { "\"$it\": [\n${getAttribute(it)}\n]" } )
        )
    }
}
