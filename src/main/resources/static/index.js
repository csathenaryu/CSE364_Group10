// *** Movie Container의 html 구조 ***
// $container는 $bricklayer 안에 들어감 => $container를 다 넣은 후 Bricklayer 실행할 것
//
// <div class="container">
//     <h2 class="title">{movie title}</h2>
//     <div class="poster poster-width">
//         <a href="{imdb link}">
//             <img src="{poster link}" alt="No Image">
//         </a>
//     </div>
// </div>
// 


class MovieContainer{
    constructor(){
        this.$layer = document.getElementsByClassName('bricklayer')[0];
    }

    makeView(movieList){
        for(let movie of movieList){
            const title = movie.title;
            const imdb = movie.imdb.slice(1, -1);
            const poster = movie.poster_url;
            this.makeMovieContainer(title, imdb, poster);
        }
    }

    makeMovieContainer(title, imdb, poster){
        const $poster = this.makeMovie(imdb, poster);
        const $title = this.makeTitle(title);
        const $container = this.makeContainer($title, $poster);
        this.$layer.appendChild($container);
    }

    makeContainer($title, $poster){
        const $div = document.createElement('div');
        $div.classList.add('container');
        $div.appendChild($title);
        $div.appendChild($poster);
        return $div;
    }

    makeTitle(title){
        const $div = document.createElement('div');
        $div.classList.add('title');
        $div.innerText = title;
        return $div;
    }

    makeMovie(imdb, poster){
        const $div = document.createElement('div');
        $div.classList.add('poster');
        $div.classList.add('poster-width');

        const $imdb = this.makeImdb(imdb);
        const $poster = this.makePoster(poster);
        $imdb.appendChild($poster);
        $div.appendChild($imdb);
        return $div;
    }

    makeImdb(imdb){
        const $a = document.createElement('a');
        // imdb 에서 괄호가 빠진 경우 수정할 것!!!!!
        $a.href = imdb;
        return $a;
    }

    makePoster(poster){
        const $img = new Image();
        $img.src = poster;
        $img.alt = "No Image";

        $img.onerror = () => {
            $img.src = "data:image/png;base64,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"
        }
        return $img;
    }
}

function getMovieList(){
    return fetch("http://localhost:8080/users/recommendations.html?gender=&age=&occupation=&genres=")
    .then((response) => {
        return response.json();
    })
}

async function loading() {
    const movieContainer = new MovieContainer();
    const list = await getMovieList();
    console.log(list);
    movieContainer.makeView(list);
}

loading();