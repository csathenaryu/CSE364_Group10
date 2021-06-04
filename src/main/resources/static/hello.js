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

    getOriginalTitle(title){
        let target;
        for(let elem of this.movieList){
            const e = this.changeToLower(elem);
            if(this.hasSubString(e, title) !== -1){
                target = elem;
                break;
            }
        }
        return target;
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
a
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
        const list = this.model.getCandidate(str, 20);
        this.view.updateMovieElement(list);
    }

    async reportSelectedMovie(title){
        console.log(title);
        const list = await this.getList(title);
        console.log(list);
    }

    getList(title){
        const url = this.getUrl(title);
        return fetch(url, {
            'Content-Type': 'application/x-www-form-urlencoded',
        })
        .then((response) => {
            return response.json();
        })
    }

    getUrl(title){
        let paramString = "movies/recommendations?limit=15"
        let searchParams = new URLSearchParams(paramString);
        searchParams.append("title", title);
        return searchParams.toString();
    }
}

function getMovieList(){
    return fetch("http://localhost:8080/movies")
    .then((response) => {
        return response.json();
    })
}

async function main(){
    const movieList = await getMovieList();
    manager = new Manager(movieList);
}

main();