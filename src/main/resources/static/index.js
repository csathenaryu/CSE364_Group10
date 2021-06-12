
const $movie = document.getElementsByClassName('movie')[0];
const $user = document.getElementsByClassName('user')[0];

const $btnMovie = document.getElementById('movie-search-button');
const $btnUser = document.getElementById('user-search-button');

function show($tag){
    $tag.classList.add('unfold')
    $tag.style.display = ''
}

function hide($tag){
    $tag.classList.remove('unfold')
    $tag.style.display = 'hidden'
}


show($movie)
//hide($user)
show($user)

$btnMovie.onclick = () => {
    show($movie)
    hide($user)
}

$btnUser.onclick = () => {
    show($user)
    hide($movie)
}