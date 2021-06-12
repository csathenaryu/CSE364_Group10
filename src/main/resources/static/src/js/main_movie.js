import {UrlEncoder} from './UrlEncoder.js'

class MovieSearch{
    constructor(list){
        this.movieList = list;
    }

    getCandidate(subStr, limit = 10000){
        const foundList = [];
        const sub = this.changeToLower(subStr)
        
        for(let elem of this.movieList){
            const target = this.changeToLower(elem);
            if(this.hasSubString(target, sub) !== -1){
                foundList.push(elem);
            }
            if(foundList.length >= limit){
                break;
            }
        }

        return foundList;
    }

    hasSubString(targetStr, subStr){
        return targetStr.indexOf(subStr);
    }

    changeToLower(str){
        return str.toLowerCase();
    }
}


class MovieSearchView{
    constructor(manager){
        this.manager = manager;
        this.$list = document.getElementsByClassName('movie-list')[0];
        this.$input = document.getElementsByTagName('input')[0];
        this.setInputAction();
    }

    updateMovieElement(titleList){
        this.removeAllMovieElement();
        for(let title of titleList){
            const $li = this.makeMovieElement(title);
            this.setLiAction($li);
            this.$list.appendChild($li);
        }
    }

    makeMovieElement(title){
        const $elem = document.createElement('li');
        $elem.classList.add('movie-element');
        $elem.innerText = title;
        return $elem;
    }

    removeAllMovieElement(){
        const $items = document.getElementsByClassName('movie-element');
        for(let i=$items.length - 1; i>=0; i--){
            $items[i].remove();
        }
    }

    setInputAction(){
        this.$input.onkeyup = () => {
            console.log(this.$input.value)
            this.reportInput(this.$input.value);
        }
    }

    setLiAction($li){
        $li.onclick = () => {
            this.reportSelectedMovie($li.innerText);
        }
    }

    reportInput(str){
        this.manager.reportInput(str);
    }

    reportSelectedMovie(title){
        this.manager.reportSelectedMovie(title);
    }
}



class Manager{
    constructor(movieList){
        this.model = new MovieSearch(movieList);
        this.view = new MovieSearchView(this);
    }

    reportInput(str){
        const list = this.model.getCandidate(str, 5);
        this.view.updateMovieElement(list);
    }

    reportSelectedMovie(title){
        console.log(title);

        const list = {
            "title": [title],
            "limit": ["20"],
        }
        
        const movieRecUrlHead = "http://localhost:8080/movies/recommendations.html";
        const urlencoder = new UrlEncoder();

        const movieUrl = urlencoder.getUrl(movieRecUrlHead, list);
        console.log("selected hashtag: ", list);
        console.log("url: ", movieUrl);
        location.href = movieUrl;
    }
}

function getMovieList(){
    return fetch("http://localhost:8080/movies/title")
    .then((response) => {
        return response.json();
    })
}

async function makeView(){
    const movieList = await getMovieList();
    const manager = new Manager(movieList);
}

makeView();

