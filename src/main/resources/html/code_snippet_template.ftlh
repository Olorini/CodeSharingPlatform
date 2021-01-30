<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="UTF-8"/>
        <link rel="stylesheet"
              href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
        <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
        <script>hljs.initHighlightingOnLoad();</script>
        <style>
            #load_date {
                font-family: Geneva, Arial, Helvetica, sans-serif;
                font-size: 13px;
                color: #458254;
            }
            #code_snippet {
                font-family: Geneva, Arial, Helvetica, sans-serif;
                font-size: 12px;
                background: #efefef;
                border: solid #868686 1px;
                padding: 10px 5px 10px 10px;
                margin-top: 2px;
                width: 50%;
                height: auto;
            }
        </style>
    </head>
    <body>
        <span id="load_date"><#if snippet.createDate??>${(snippet.createDate)?string("dd/MM/yyyy HH:mm:ss")}<#else> </#if></span>
        <pre id="code_snippet">
            <code>
                <#if snippet.code??>${snippet.code}<#else> </#if>
            </code>
        </pre>
    </body>
</html>