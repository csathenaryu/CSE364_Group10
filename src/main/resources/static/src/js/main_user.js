// API 에서 받아온 hashtag list
// value 순서가 중요 => hashtag를 정렬할 때 해당 순서를 기준으로 함
/*
response = {[
    {
        type : genres,
        value : [ action, adventure, ... ]
    },
    {
        type : occupation,
        value : [ other, educator, ... ]
    },
    ...
]}

request = {[
    {
        type : genres,
        value : [ action, adventure, ... ]
    },
    {
        type : occupation,
        value : [ educator, teacher, ... ]
    },
    ...
]}
*/

import {HashtagSectionListView} from './HashtagSectionListView.js'
import {HashtagData} from './HashtagData.js'
import {UrlEncoder} from './UrlEncoder.js'


const hashtagList = [
    {
        type : "genres",
        value : [{"name":"Action","dataName":"action"}, {"name":"Adventure","dataName":"adventure"}, {"name":"Animation","dataName":"animation"}, {"name":"Children's","dataName":"children's"}, {"name":"Comedy","dataName":"comedy"}, {"name":"Crime","dataName":"crime"}, {"name":"Animation","dataName":"documentary"}, {"name":"Drama","dataName":"drama"}, {"name":"Fantasy","dataName":"fantasy"}, {"name":"Film-noir","dataName":"film-noir"}, {"name":"Horror","dataName":"horror"}, {"name":"Musical","dataName":"musical"}, {"name":"Mystery","dataName":"mystery"}, {"name":"Romance","dataName":"romance"}, {"name":"Scifi","dataName":"scifi"}, {"name":"Thriller","dataName":"thriller"}, {"name":"War","dataName":"war"}, {"name":"Western","dataName":"western"}],
    },
    {
        type : "gender",
        value : [{"name":"Male","dataName":"m"}, {"name":"Female","dataName":"f"}],
    },
    {
        type : "age",
        value : [{"name":"0-18", "dataName":"1"}, {"name":"19-25", "dataName":"19"}, {"name":"26-35", "dataName":"26"}, {"name":"36-40", "dataName":"36"}, {"name":"41-45", "dataName":"41"}, {"name":"46-50", "dataName":"46"}, {"name":"51-55", "dataName":"51"}, {"name":"56-65", "dataName":"56"}, {"name":"65+", "dataName":"66"}],
    },
    {  
        type : "occupation",
        value : [{"name":"Academic", "dataName":"academic"}, {"name":"Educator", "dataName":"educator"}, {"name":"Artist", "dataName":"artist"}, {"name":"Clerical", "dataName":"clerical"}, {"name":"Admin", "dataName":"admin"}, {"name":"College Student", "dataName":"collegestudent"}, {"name":"Grad Student", "dataName":"gradstudent"}, {"name":"Customer Service", "dataName":"customerservice"}, {"name":"Doctor", "dataName":"doctor"}, {"name":"Healthcare", "dataName":"healthcare"}, {"name":"Executive", "dataName":"executive"}, {"name":"Managerial", "dataName":"managerial"}, {"name":"Farmer", "dataName":"farmer"}, {"name":"Homemaker", "dataName":"homemaker"}, {"name":"K-12 Student", "dataName":"k-12student"}, {"name":"Lawyer", "dataName":"lawyer"}, {"name":"Programmer", "dataName":"programmer"}, {"name":"Retired", "dataName":"retired"}, {"name":"Sales", "dataName":"sales"}, {"name":"Marketing", "dataName":"marketing"}, {"name":"Scientist", "dataName":"scientist"}, {"name":"Self-employed", "dataName":"self-employed"}, {"name":"Technician", "dataName":"technician"}, {"name":"Engineer", "dataName":"engineer"}, {"name":"Tradesman", "dataName":"tradesman"}, {"name":"Craftsman", "dataName":"craftsman"}, {"name":"Unemployed", "dataName":"unemployed"}, {"name":"Writer", "dataName":"writer"}, {"name":"Other", "dataName":"other"}],
    },
];


class Mediator{
    constructor(hashtagList){
        this.hashData = new HashtagData(hashtagList);
        this.hashView = new HashtagSectionListView(this.hashData.getAllHashtagList());
        this.hashData.registerObserver(this);
        this.hashView.registerObserver(this);
    }

    update(type, value){
        console.log(`event reported: ${type}, ${value}`);
        this.hashView.update(type, value);
    }

    report(type, value){
        console.log(`button clicked: ${type}, ${value}`);
        this.hashData.setSelected(type, value);
    }

    getSelectedList(){
        return this.hashData.getSelectedHashtagList();
    }
}

const mediator = new Mediator(hashtagList);

const movieRecUrlHead = "http://localhost:8080/users/recommendations.html";
const urlencoder = new UrlEncoder();


function getRecommendedMovies(url){
    return fetch(url, {
        'Content-Type': 'application/x-www-form-urlencoded',
    })
    .then((response) => {
        return response.json();
    })
}

const $btn = document.getElementsByClassName('search')[0];
$btn.onclick = async () => {
    const selectedHashtagList = mediator.getSelectedList();
    const movieUrl = urlencoder.getUrl(movieRecUrlHead, selectedHashtagList);
    console.log("selected hashtag: ", selectedHashtagList);
    console.log("url: ", movieUrl);
    location.href = movieUrl;
}
