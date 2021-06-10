<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Latest</title>
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
    <#list codes as code>
        <div>
            <span class="time">${code.updateDate}</span>
            <br>
            <pre class="code"><code>${code.code}</code></pre>
        <div>
    </#list>
</body>
</html>