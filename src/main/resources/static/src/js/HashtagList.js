import {Hashtag} from './Hashtag.js'


export class HashtagList{

    constructor(type, valueList, hashtagData, double){
        this.list = {};
        this.type = type;
        this.hashtagData = hashtagData;
        console.log(valueList);
        for(let i=0; i<valueList.length; i++){
            const hashtag = valueList[i];
            this.list[hashtag.name] = new Hashtag(hashtag.dataName, hashtag.name, i, this);
        }
        this.doublySelected = double;
    }

    setSelected(hashtagName){
        if(!this.doublySelected){
            const selectedName = this.getSelected();
            if(selectedName && (selectedName !== hashtagName)){
                this.list[selectedName].setSelected();
            }
        }
        this.list[hashtagName].setSelected();
    }

    getSelected(){
        let name = null;
        for(let hashtagName in this.list){
            const hashtag = this.list[hashtagName];
            if(hashtag.getSelected()){
                name = hashtagName;
                break;
            }
        }
        return name;
    }

    report(value){
        this.hashtagData.report(this.type, value);
    }

    getHashtag(hashtagName){
        return this.list[hashtagName];
    }

    sortBySelected(a, b){
        if(a.getSelected() === b.getSelected()){
            if(a.getOrder() < b.getOrder()){
                return -1;
            } else if(a.getOrder() > b.getOrder()){
                return 1;
            } else{
                return 0;
            }
        } else{
            if(a.getSelected()){
                return -1;
            } else{
                return 1;
            }
        }
    }

    getAllHashtagList(){
        const list = [];
        for(let temp in this.list){
            list.push(this.list[temp]);
        }
        list.sort((a, b) => {
            return this.sortBySelected(a, b);
        });
        const hashtagList = [];
        for(let hashtag of list){
            hashtagList.push({
                name : hashtag.name,
                order : hashtag.order,
            });
        }
        return hashtagList;
    }

    getSelectedHashtagList(){
        const selectedHashtag = [];
        for(let hashtagName in this.list){
            const hashtag = this.list[hashtagName];
            if(hashtag.getSelected()){
                selectedHashtag.push(hashtag.getDataName());
            }
        }
        return selectedHashtag;
    }
}

