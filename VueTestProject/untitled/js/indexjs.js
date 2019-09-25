/**
 * Created by ponomarev_ia on 19.09.2019.
 */

new Vue({
    el: '#app',
    data: {
        newTodo: '',
        todos: [
            { text: 'Add some todos' }
        ]
    },
    methods: {
        addTodo: function () {
            var text = this.newTodo.trim()
            if (text) {
                this.todos.push({ text: text })
                this.newTodo = ''
            }
        },
        removeTodo: function (index) {
            this.todos.splice(index, 1)
        }
    }
})

var exampleVM = new Vue({
    el: '#example-1',
    data: function ()
     { 
         return {
             name: "ilyaponomarev",
             styleObject: {
                 color: 'red',
                 height: '100px',
                 margin: 'auto'
             }
         }
    }
})

var count = 1
var cssCount = 1

var exampleVM2 = new Vue({
    el: '#example-2',
    data: {
        greeting: false,
        isActive: true,
        hasError: false
    },
    methods: {
        reverse: function () {
            this.greeting = count%2
            count++
        },
        sendRequest: function () {
            var http = new XMLHttpRequest();
            var url = 'http://localhost:8080/users';
            http.open('GET', url, true);
            http.setRequestHeader('Content-type', 'application/json');
            http.onreadystatechange= function () {//Call a function when the state changes.
                if (http.readyState == 4 && http.status == 200) {
                    var result = JSON.parse(http.responseText)
                    alert("sendRequest function: Внимание отправлен простой запрос \n " + http.responseText);
                }
            }
            http.send()
        },
        dinamicCssControl: function () {
            this.isActive = cssCount%2
            cssCount++
        }
    }
})

var h = new Vue({
    el: '#h1',
    data: {
        ok: true //регулируем видимость h1
    }
})

var ff = new Vue({
    el: '#firstForm',
    data:{
        name:""
    },
    methods:{
        alertAction: function () {
            alert(this.name.trim())
        }
    }
})