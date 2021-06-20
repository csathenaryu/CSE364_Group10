
const $btn_home = document.getElementsByClassName('home-button')[0];
$btn_home.onclick = async () => {
    const searchUrl = "http://localhost:8080/index.html";
    console.log("url: ", searchUrl);
    location.href = searchUrl;
}

const $btn_movie = document.getElementsByClassName('movie-button')[0];
$btn_movie.onclick = async () => {
    const searchUrl = "http://localhost:8080/movies/index.html";
    console.log("url: ", searchUrl);
    location.href = searchUrl;
}


const $btn_user = document.getElementsByClassName('user-button')[0];
$btn_user.onclick = async () => {
    const searchUrl = "http://localhost:8080/users/index.html";
    console.log("url: ", searchUrl);
    location.href = searchUrl;
}

const $btn_lucky = document.getElementsByClassName('lucky-button')[0];
$btn_lucky.onclick = async () => {
    const searchUrl = "http://localhost:8080/feelinglucky/index.html";
    console.log("url: ", searchUrl);
    location.href = searchUrl;
}