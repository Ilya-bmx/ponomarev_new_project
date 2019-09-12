function getMethod1() {
    var secName = "secname";
    var name = "name";
    var status = "status";
    var elem = document.getElementById('InputButton');
    elem.addEventListener('click', getN);
}
function getN() {
    var getNum2 = document.getElementById('textInput2').value;
    var getNum1 = document.getElementById('textInput1').value;
    var getNum = document.getElementById('textInput').value;
    status = getNum2;
    secName = getNum1;
    name = getNum;
    otherHttpFunc();
}
function otherHttpFunc() {
    var json = JSON.stringify({
        id: 01,
        name: name,
        secondName: secName,
        status: status
    });
    var http = new XMLHttpRequest();
    var url = 'http://localhost:8080/users/accept';
    var params = json;
    http.open('POST', url, true);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(params);

    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState == 4 && http.status == 200) {
            var user = JSON.parse(http.responseText);
            alert(user.name + "entered in backend");
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
            var user1 = eval("(" + request.responseText + ")")//неплохо обрабатывает JSON
            /*console.log(request.responseText);*/
            console.log(user1);
            document.getElementById("id").innerHTML = user.id;
            document.getElementById("name").innerHTML = user.name;
            document.getElementById("secondName").innerHTML = user.secondName;
        }
    };
    request.setRequestHeader("Content-type", "application/json");
    request.send(params);
}