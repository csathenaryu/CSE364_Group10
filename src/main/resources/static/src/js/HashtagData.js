import {HashtagList} from './HashtagList.js'

/*
input = [
    {
        type : genre,
        value : [action, adventure, ...]
    },
    {
        type : genre,
        value : [action, adventure, ...]
    }
]

해당 input을 객체에 저장
저장할 때 prop = type, value = hashtagList(value)

HashtagData.data = {
    type : hashtagList(value),
    genre : hashtagList([action, adventure, ...])
    occupation : hashtagList([educator, teacher, ...])
}
*/

export class HashtagData{

    constructor(hashtagList){
        this.data = {};
        this.observers = [];

        for(let typeValue in hashtagList){
            this.addNewData(hashtagList[typeValue]);
        }
    }

    addNewData({type, value}){
        this.data[type] = this.makeNewHashtagList(type, value);
    }

    makeNewHashtagList(type, value){
        let a;
        if(type === "genres"){
            a = new HashtagList(type, value, this, true);
        } else{
            a = new HashtagList(type, value, this, false);
        }
        return a;
    }

    setSelected(type, value){
        const hashtag = this.data[type].setSelected(value);
    }

    report(type, value){
        this.notifyingObservers(type, value);
    }

    getAllHashtagList(){
        const hashtag = {};
        for(let type in this.data){
            hashtag[type] = this.data[type].getAllHashtagList();
        }
        return hashtag;
    }

    getSelectedHashtagList(){
        const selectedHashtag = {};
        for(let type in this.data){
            selectedHashtag[type] = this.data[type].getSelectedHashtagList();
        }
        return selectedHashtag;
    }

    registerObserver(observer){
        this.observers.push(observer);
    }

    notifyingObservers(type, value){
        for(let observer of this.observers){
            observer.update(type, value);
        }
    }
}