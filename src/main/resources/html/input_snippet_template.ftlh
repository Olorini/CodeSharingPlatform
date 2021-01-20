<!DOCTYPE html>
<html>
    <head>
        <title>${title}</title>
        <meta charset="UTF-8"/>
        <style>
            #code_snippet {
                font-family: Geneva, Arial, Helvetica, sans-serif;
                font-size: 12px;
                padding: 10px 5px 10px 10px;
                width: 50%;
                min-height: 150px;
            }
        </style>
    </head>
    <body>
        <form action="" method="post">
            <label for="code_snippet"></label><textarea id="code_snippet" placeholder="write your code here"></textarea>
        </form>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </body>
    <script>
		function send() {
			let object = {
				"code": document.getElementById("code_snippet").value
			};
			let json = JSON.stringify(object);
			let xhr = new XMLHttpRequest();
			xhr.open("POST", '/api/code/new', false)
			xhr.setRequestHeader('Content-type', 'application/json; charset=utf-8');
			xhr.send(json);
			if (xhr.status === 200) {
				document.getElementById("code_snippet").value = "DONE!";
			}
		}
    </script>
</html>