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
            div {
                margin: 10px 10px 10px 0px;
            }
        </style>
    </head>
    <body>
        <form action="" method="post">
            <label for="code_snippet"></label><textarea id="code_snippet" placeholder="write your code here"></textarea>
            <div>
                <label for="time_restriction">Time restriction: </label><input id="time_restriction" pattern="[0-9]+" type="text"/>
            </div>
            <div>
                <label for="views_restriction">Views restriction: </label><input id="views_restriction" pattern="[0-9]+" type="text"/>
            </div>
        </form>
        <button id="send_snippet" type="submit" onclick="send()">Submit</button>
    </body>
    <script>
		function send() {
			let object = {
				"code": document.getElementById("code_snippet").value,
				"time": document.getElementById("time_restriction").value,
				"views": document.getElementById("views_restriction").value
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