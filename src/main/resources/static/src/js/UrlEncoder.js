export class UrlEncoder{

    getUrl(head, hashtagList){
        return head + "?" + this.setOptions(hashtagList);
    }

    setOptions(hashtagList){
        let components = ""
        for(let sectionName in hashtagList){
            let component = this.setOption(sectionName, hashtagList[sectionName]);
            components += this.addAndOperator(component);
        }
        return components.slice(0, -1);
    }

    setOption(sectionName, properties){
        const component = sectionName + "=";
        let prop = "";
        if(properties.length !== 0){
            let temp = this.linkProperty(properties);
            prop = encodeURIComponent(temp);
        } 
        return component + prop;
    }

    addAndOperator(component){
        return component += "&";
    }

    linkProperty(property){
        let prop = "";
        const size = property.length;
        for(let i=0; i<size-1; i++){
            prop += (property[i] + "|");
        }
        prop += property[size-1];
        return prop;
    }
 
}




// Test code

/*
const list = {
    "age": ["41-45"],
    "gender": ["Female"],
    "genres": ["Animation", "Romance"],
    "occupation": ["K-12 Student"]
}

const head = "http://localhost:8080/users/recommendations";
const encoder = new UrlEncoder();
console.log(encoder.getUrl(head, list));

*/
