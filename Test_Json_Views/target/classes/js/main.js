function getMethod1() {
    var secName = "name";
    var elem = document.getElementById('InputButton');
    elem.addEventListener('click', getN);
}
function getN() {
    var getNum = document.getElementById('textInput').value;
    secName = getNum;
    otherHttpFunc();
}
function otherHttpFunc() {
    var json = JSON.stringify({
        id: 01,
        name: secName,
        secondName: "Pon",
        status: "status"
    });
    var http = new XMLHttpRequest();
    var url = 'http://localhost:8080/users/accept';
    var params = json;
    http.open('POST', url, true);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(params);

    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState == 4 && http.status == 200) {
            //alert(http.responseText);
        }
    }
}

function showMessage() {
    var request = new XMLHttpRequest();
    var params = "{ \"id\" : \"01\", \"name\" : \"Ilya\", \"secondName\" : \"Ponomarev\", \"status\" : \"hotel\" }";//{ "id" : "01", "name" : "Ilya", "secondName" : "Ponomarev", "status" : "hotel" };//"action=something";
    request.open('POST', 'http://localhost:8080/users', true);
    request.onreadystatechange = function () {
/*        if (request.readyState == 4) {   для проверки на работоспособность
            alert("It worked!");
        }*/

        if (request.status == 201) {
            document.getElementById("mainP").innerHTML = "GetUser";
            var user = JSON.parse(request.responseText);
            document.getElementById("id").innerHTML = user.id;
            document.getElementById("name").innerHTML = user.name;
            document.getElementById("secondName").innerHTML = user.secondName;
        }
    };
    request.setRequestHeader("Content-type", "application/json");
    request.send(params);
}