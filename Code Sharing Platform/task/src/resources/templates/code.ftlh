<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Code</title>
    <link rel="stylesheet"
           href="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/styles/default.min.css">
    <script src="//cdn.jsdelivr.net/gh/highlightjs/cdn-release@10.2.1/build/highlight.min.js"></script>
    <script>hljs.initHighlightingOnLoad();</script>
    <style>
        .time {
            color: green;
            margin-bottom: 0;
        }

        .code {
            display: inline-block;
            background-color: gainsboro;
            border: 1px solid black;
            padding: 5px;
            margin-top: 0;
        }
    </style>
</head>
<body>
    <#if views == -1>
            <span id="views_restriction">0 more views allowed</span>
            <br>
    </#if>
    <#if views gt 0>
        <span id="views_restriction">${views} more views allowed</span>
        <br>
    </#if>
    <#if time gt 0>
        <span id="time_restriction">The code will be available for ${time} seconds</span>
        <br>
    </#if>
    <span id="load_date" class="time">${date}</span>
    <br>
    <pre id="code_snippet" class="code"><code>${code}</code></pre>
</body>
</html>