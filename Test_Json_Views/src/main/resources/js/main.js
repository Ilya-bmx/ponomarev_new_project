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
    password = getNum1;
    name = getNum;
    otherHttpFunc();
}
function otherHttpFunc() {
    var json = JSON.stringify({
        name: name,
        password: password,
        active: true,
        role: "USER"
    });
    var http = new XMLHttpRequest();
    var url = 'http://localhost:8080/users/accept1';
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
var principalRole;
function checkRole() {
    var http = new XMLHttpRequest();
    var url = 'http://localhost:8080/users/role';
    http.open('POST', url, true);
    http.setRequestHeader('Content-type', 'application/json');
    http.onreadystatechange = function () {
        principalRole = http.responseText;
        if (principalRole != null) {
            if (principalRole == "2") {
                document.getElementById("admin").style.display = 'none';
            }
            if (principalRole == "1") {
                document.getElementById("user").style.display = 'none';
            }
        }
    }
    http.send();
}

function saveDraft() {
    alert("NACHAL")
    var number;
    var letter;
    var flag;
    var elem = document.getElementById('InputBut');
    elem.addEventListener('click', getNR);
}
function getNR() {
    var getNum2 = document.getElementById('textNumber').value;
    var getNum1 = document.getElementById('textLetter').value;
    var getNum = document.getElementById('textFlag').value;
    number = getNum2;
    letter = getNum1;
    flag = getNum;//true always
    otherHttpFunc1();
}
function otherHttpFunc1() {
    var json1 = JSON.stringify({
        number: number,
        letter: letter,
        flag: (flag == "true" || flag == "false") ? true : false
    });
    var http = new XMLHttpRequest();
    var url = 'http://localhost:8080/users/worker/save';
    var params1 = json1;
    http.open('POST', url, true);
    http.setRequestHeader('Content-type', 'application/json');
    http.send(params1);

    http.onreadystatechange = function () {//Call a function when the state changes.
        if (http.readyState == 4 && http.status == 200) {
            alert("entered in backend");
        }
    }
}