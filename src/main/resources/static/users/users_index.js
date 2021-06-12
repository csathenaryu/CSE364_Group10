
const $user = document.getElementsByClassName('user')[0];

function show($tag){
    $tag.classList.add('unfold')
    $tag.style.display = ''
}

function hide($tag){
    $tag.classList.remove('unfold')
    $tag.style.display = 'hidden'
}


show($user)
