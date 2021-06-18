# VIM PROJECTOR (CSE364_Group10)
<br>

## Introduction - Milestone 3
`VIM PROJECTOR` is a **movie recommendation RESTful API** which recommends **Very Impressive Movies** according to given conditions.  
There are two ways you can get movie recommendations using `VIM PROJECTOR`.  

1.  Input `user information`  
Give the `genres` you wish to watch and your `gender`, `age` and `occupation`. Then you will receive a list of **10 recommended movies** that belong to the given genres.<br>
    To see the list of available input, please see [here](#Input-User-Information).
    

2. Input `movie information`  
Give a `movie title` and the `number of movies` you wish to be recommended. Then you will get the given number of movies similar to the one you gave us.<br>
   To see more detail of inputs, please see [here](#Input-Movie-Information).

Using `VIM PROJECTOR` will give you a list of recommended movies with information such as titles, genres, and links to imdb.  
Find your next movie to watch with `VIM PROJECTOR`!!!  

If you want more information about our **recommendation algorithm**, please see [here](#Recommendation-Algorithm).  


<br>
<br>


## Index

+ [Introduction](#Introduction---Milestone-3)
+ [Index](#Index)
+ [Installation](#Installation)
+ [API Guide](#API-Guide)
    + [Common](#Common)
    + [Input User Information](#Input-User-Information)
    + [Input Movie Information](#Input-Movie-Information)
+ [Recommendation Algorithm](#Recommendation-Algorithm)
    + [Introduction](#Introduction)
    + [Details](#Details)
+ [Contributors](#Contributors)

<br>
<br>


## Installation

`Java(version 11 or up)`, `Git`, `curl` and `Maven` installation must be preceded before installing and running this program.  
To install and this program, run the commands below, or run the [`run.sh`](./run.sh) file that is included in the repository.  
```
$ git clone https://github.com/csathenaryu/CSE364_Group10.git
$ git pull origin main
$ git checkout main
$ mvn package
$ java -jar target/CSE364-project-0.0.1-SNAPSHOT/
```

Please refer to the following sections about details in [calling this API](#API-Guide).
<br>
<br>
<br>

## Website Guide

### Homepage
 - 스크린샷 첨부
### Recommended by User

### Recommended by Movie

### Feeling Lucky
- 구현 후 작성

## API Guide

### Common

**[HTTP Header]**  
Following items should be set at the HTTP Header to call API.  
|Name|Required|Value|
|:---|:---|:---|
|Content-type|mandatory|application/json|
<br>

**[Request]**
- Input of GET request should be in **JSON** type.  
- Please refer to `list of available input`. The input which is not in the list might be ignored or miscategorized.  
- **Capitalization** does not matter. However, in the case of `movie title`, capitalization does matter.  
- Please **avoid spelling mistakes.** This program does not provide any fool-proof feature for such mistakes.  
- If there is **no valid input** in specific fields, the program will search movies regardless of the fields.  
<br>

### Input User Information

**[API Basic Information]**
|Method|Authentication|url|Output Format|
|:---|:---|:---|:---|
|GET|-|http://localhost:8080/users/recommendations|JSON|
<br>
  
**[Request Body]**
|Name|Type|Required|Value|Description|
|:---|:---|:---|:---|:---|
|gender|string|mandatory|All gender (default)|Wanted gender. Set this field as `""` for default option.|
|age|integer|mandatory|All age (default)|Wanted age. Set this field as `""` for default option.|
|occupation|string|mandatory|All occupation (default)|Watned occupation. Set this field as `""` for default option.|
|genres|string|mandatory|All genres (default)|Wanted genres. Set this field as `""` for default option.|
<br>

- Gender  
    - **List of available input**: `m`, `f`
- Age
    - **List of available input**: `0 or higher`
    - **Ages that are not integer or negative** will be ignored.
- Occupation
    - **List of available input**: `academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`, `other`
    - **Occupations that are not in the list above** will be categorized as `other`.
    - For an `occupation` that consists of **more than one word**, omit the space in between and **input as one word**.
- Genres
    - **List of available input**: `action`, `adventure`, `animation`, `children's`, `comedy`, `crime`, `documentary`, `drama`, `fantasy`, `film-noir`, `horror`, `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`
    - **Genres that are not in the list above** will be ignored when entered.
    - In case you are entering **several genres**, please distinguish each genre by delimiter `|`.
    - **Repeated inputs** will be ignored.
    - Please **do not include spaces** in between genres.
    - If there is **no valid genre input**, the program will search movie regardless of genres.

<br>

**[Response Body]**
|Key|Type|Description|
|:---|:---|:---|
|title|string|Titles of recommended movies|
|genres|string|Genres of recommended movies. Genres are separated by delimiter `\|`|
|imdb|string|Link of receommended movies|
<br>


**[Sample Request]**
```
curl -X GET http://localhost:8080/users/recommendations -H 'Content-type:application/json' -d '{"gender":"F", "age":"25", "occupation":"educator", "genres":"Action|War"}'
```
<br>
  
**[Sample Response]**
```
[{
    "title": "Toy Story (1995)",
    "genres": "Animation|Children's|Comedy",
    "imdb": "https://www.imdb.com/title/tt0114709"
},
{
    "title": "xxx",
    "genres": "xxx",
    "imdb": "https://www.imdb.com/title/ttXXXXXXX"
},
{
    "title": "xxx",
    "genres": "xxx",
    "imdb": "https://www.imdb.com/title/ttXXXXXXX"
},
...
]
```
<br>


### Input Movie Information

**[API Basic Information]**
|Method|Authentication|url|Output Format|
|:---|:---|:---|:---|
|GET|-|http://localhost:8080/movies/recommendations|JSON|
<br>
  
**[Request Body]**
|Name|Type|Required|Value|Description|
|:---|:---|:---|:---|:---|
|title|string|mandatory|Title of Movie|ISO-8859-15 encoding required|
|limit|integer|optional|10 (default)|Number of recommended movies|
<br>


- Title
    - **List of available input**: Movie titles in `movies.dat` of data directory.
    - **Invalid input case**: Titles that are not in `movies.dat` (e.g., typo) or empty title.
    - If input title is **invalid**, the program will recommend from the entire dataset.
- Limit
    - **List of available input**: Integers
    - If limit is **zero or less than zero**, the program will recommend 10 movies.

<br>

**[Response Body]**
|Key|Type|Description|
|:---|:---|:---|
|title|string|Titles of recommended movies|
|genres|string|Genres of recommended movies. Genres are separated by delimiter `\|`|
|imdb|string|Link of receommended movies|
<br>

**[Sample Request]**
```
curl -X GET http://localhost:8080/movies/recommendations -H 'Content-type:application/json' -d '{"title":"Toy Story (1995)", "limit":20}'
```
<br>

**[Sample Response]**
```
[{
    "title": "Toy Story (1995)",
    "genres": "Animation|Children's|Comedy",
    "imdb": "https://www.imdb.com/title/tt0114709"
},
{
    "title": "xxx",
    "genres": "xxx",
    "imdb": "https://www.imdb.com/title/ttXXXXXXX"
},
{
    "title": "xxx",
    "genres": "xxx",
    "imdb": "https://www.imdb.com/title/ttXXXXXXX"
},
...
]
```
<br>
<br>


## Recommendation Algorithm

### Introduction
The criteria for recommending movies in `VIM PROJECTOR` are `rating` and `count`.  

- `rating` is the average score of a movie that belongs to `genres` you input (`target genres`) and is rated by users belonging to your categories of `gender`, `age`, and `occupation` (`target users`).  
- `count` is the number of ratings of a movie that belongs to the target genres and rated by the target users.  

In our algorithms, movies with **high rating averages** will be selected as recommendations. In each step in the algorithms, rating data of the movies that fit the conditions (`target movies`) will be processed and sorted. Top N movies in the sorted list will be selected to be recommended, with N being the number of movies required.  

For a movie to be recommended, the `rating average` of a movie must be **equal or above 3.5**, and the `count` of the movie must be **equal or above 1%** of the number of total rating data.  
<br>


### Details

`VIM PROJECTOR` uses two different movie recommendation algorithms depending on different inputs, `user information` and `movie information`.  

1. Input `user information`  

    By `user information`, we mean your `gender`, `age`, `occupation` and `genres` you wish to watch.
    When given `user information`, `VIM PROJECTOR` will recommend movies that are in the `target genres` and have high ratings by `target users`.  
    The steps in this algorithm are shown below.  

    1.  Generate a list of 10 recommended movies that are in the `target genres` and are rated highly by `target users`. If `target genres` are not given, the algorithm will search for and recommend movies in all genres.  
    2.  If the number of movies in the generated list is less than 10, then expand the range of `target users` by expanding the range of `occupation` to **all occupations** and search again. Then add the recommended movies to the list.      
    3.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` again by expanding the range of `age` to **all age groups** and search again. Then add the recommended movies to the list.  
    4.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` once more by expanding the range of `gender` to **all genders** and search again. Then add the recommended movies to the list.
    <br>
    <details>
    <summary>See an example</summary>
    <div markdown="1">

    Let’s see an example of {`gender`: “m”, `age`: “56”, `occupation`: “k-12student”, `genres`: “” } given as an input of `user information`. Since the genres field is not given, movies in all genres will be the candidates for recommendation.

    According to this algorithm, the generation of a movie recommendation list would follow the process shown below.

    1.  A list of recommended movies is generated using the given `target user` data and `target genres` data. In this example, there is only one `target user`. The number of movies contained in this list of recommended movies is less than 10.
    2.  Since there are less than 10 movies in the list, expand the range of `target users` by allowing the search for **all occupations**. Now the conditions for the `target users` would be {`gender`: “m”, `age`: “56”, `occupation`: “”, `genres`: “” }. Add the recommended movies based on the new range of `target users`.
    3.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` by allowing the search for **all age groups**. Now the conditions for the `target users` would be {`gender`: “m”, `age`: “”, `occupation`: “”, `genres`: “” }. Add the recommended movies based on the new range of `target users`.
    4.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` by allowing the search for **all genders**. Now the conditions for the `target users` would be {`gender`: “”, `age`: “”, `occupation`: “”, `genres`: “” }. Add the recommended movies based on the new range of `target users`.

    </div>
    </details>
    
<br>

2. Input `movie information`  

    By `movie information`, we mean a `movie title` and a `limit number`. When given `movie information`, `VIM PROJECTOR` will recommend high-rated movies similar to the given movie and up to the given `limit number`. The similarity of the movies are assessed by the `genres` of the movies.
    The steps in this algorithm are shown below.

    1.  Generate a list of recommended movies that are in the **same genres** as the given movie.
    2.  If the number of movies in the generated list is less than the given `limit number`, add the recommended movies that belong to genres that **include all the genres** of the given movie to the list.
    3.  If the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **at least one genre** of the given movie.
    4.  If the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **all genres**.
    <br>
    <details>
    <summary>See an example</summary>
    <div markdown="1">

    Let’s see an example of {`title`: “Toy Story (1995)”, `limit`: 20} given as an input of `movie information`. The `genres` of this movie are `Animation`, `Children's`, and `Comedy`. A list of 20 recommended movies should be generated.

    According to this algorithm, the generation of a movie recommendation list would follow the process shown below.

    1.  A list of recommended movies is generated from the data of movies that only belong to all three `genres` of `Animation`, `Children's`, and `Comedy` at once. The generated list contains only one movie.
    2.  Then recommendations are searched within the data of movies that belong to all three `genres` and more. One such example would be a movie that belongs to `genres` of `Animation`, `Children’s`, `Comedy` and `Action`. However, in this case there are no movies added to the list.
    3.  Now the recommendations are searched from the data of movies that belong to at least one `genre` of the given three `genres`. This means that the recommended movies could belong to any one `genre` from `Animation`, `Children’s`, or `Comedy`, or to any two of these `genres`. In this example, there are 19 movies added to the generated list.
    4.  In this example, the list of 20 recommended movies is now generated. However, if the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **all genres**.

    </div>
    </details>
    
<br>
<br>

## Contributors

|NAME<br>(STUDENT ID)|ROLE|
|:----|:--------|
|박소연<br>(20171113)|[Milestone 1]<br>- Suggested algorithm utilizing boolean ArrayList<br>- Implemented code that receives boolean ArrayList as input and returns Rating as output<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'recommender' package test codes<br>[Milestone 3]<br>- Implemented 'Mileston2Test' and 'Milestone3Test'<br>- Edit 'Milestone2' and 'Milestone3' for include limit parameter|
|안종민<br>(20171155)|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br>- Edited Pom.xml<br>[Milestone 2]<br>- Implemented 'parsinginputargs' package<br>- Implemented 'loadingdata' package test codes<br>[Milestone 3]<br>- Edit Docker for Milestone3<br>- Implement 'loadingdata' package test codes<br>- Reimplemented 'TopRating' class|
|류성화<br>(20171375)|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br> - Translated README.md<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'Milestone2.java' test codes<br>[Milestone3]<br>- Applied Spring framework<br>- Implemented 'Milestone3'<br>- Add 'stringStringloadHashFrom' method in 'FilePreprocessing.java' to get genres by title|
|김혜진<br>(20181072)|[Milestone 1]<br>- Implemented code that receives and parses arguments as inputs<br>- Wrote first draft of README.md<br>[Milestone 2]<br>- Implemented 'loadingdata' package<br>- Implemented 'customdatastructure' and 'parsinginputargs' package test codes<br>[Milestone3]<br>- Implemented 'FilteringDataByCondition'<br>- Implemented 'FilteringDataByCondition' test codes|

<br>
<br>
<br>
