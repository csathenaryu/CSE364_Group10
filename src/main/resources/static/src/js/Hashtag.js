export class Hashtag{
    constructor(dataName, name, order, hashtagList){
        this.dataName = dataName;
        this.name = name;
        this.order = order;
        this.selected = false;
        this.hashtagList = hashtagList;
    }

    getOrder(){
        return this.order;
    }

    setSelected(){
        this.selected = !this.selected;
        this.hashtagList.report(this.name);
    }

    getDataName() {
        return this.dataName;
    }

    getName(){
        return this.name;
    }

    getSelected(){
        return this.selected;
    }
}