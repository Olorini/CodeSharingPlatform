<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="UTF-8"/>
        <style>
            #load_date {
                font-family: Geneva, Arial, Helvetica, sans-serif;
                font-size: 14px;
                color: #458254;
            }
            #code_snippet {
                font-family: Geneva, Arial, Helvetica, sans-serif;
                font-size: 12px;
                background: #efefef;
                border: solid #868686 1px;
                padding: 10px 5px 10px 10px;
                width: 50%;
                min-height: 150px;
            }
        </style>
    </head>
    <body>
    <#list snippets as snippet>
        <span id="load_date"><#if snippet.createDate??>${(snippet.createDate)?string("dd/MM/yyyy HH:mm:ss")}<#else> </#if></span>
        <pre id="code_snippet"><#if snippet.code??>${snippet.code}<#else> </#if></pre>
    </#list>
    </body>
</html>