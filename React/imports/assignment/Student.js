export default class Student {
    constructor(name, age) {
        this.name = name;
        this.age = age;
    }
    showDetails() {
        console.log(this.name, this.age);
    }
}
