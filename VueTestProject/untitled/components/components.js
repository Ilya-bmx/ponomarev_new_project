Vue.component('button-counter', {
    data: function () {
        return {
            count: 0
        }
    },
    template: '<button v-on:click="count++">Счётчик кликов — {{ count }}</button>'
})

Vue.component('button-input', {
    data: function(){
        return {
            name: ""
        }
    },
    template: '<input v-model="name" placeholder="Enter a message" v-on:keyup.enter="alertAction"/>',
    methods: {
        alertAction: function () {
            alert("нажали" + this.name.trim())
        }
    }})