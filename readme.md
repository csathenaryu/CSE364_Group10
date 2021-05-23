# VIM PROJECTOR (CSE364_Group10)

  

## Introduction - Milestone 3

`VIM PROJECTOR` is a `movie recommendation program` which recommends movies according to given conditions.

There are two ways you can get movie recommendations using `VIM PROJECTOR`.

1.  Input `user information`
    

Give the `genres` you wish to watch and your `gender`, `age` and `occupation`. Then you will receive a list of `10 recommended movies` that belong to the given genres.

2. Input `movie information`

Give a `movie title` and the `number of movies` you wish to be recommended. Then you will get the given number of movies similar to the one you gave us.

Using `VIM PROJECTOR` will give you a list of recommended movies with information such as titles, genres, and links to imdb.

Find your next movie to watch with `VIM PROJECTOR`!!!

<br>

<br>

<br>

  

## Index

+ [Introduction](#Introduction)

+ [Index](#Index)

+ [Algorithm](#Algorithm)

+ [Installation](#Installation)

+ [Usage](#Usage)

+ [Common](#Common)

+ [Gender](#Gender)

+ [Age](#Age)

+ [Occupation](#Occupation)

+ [Genres](#Genres)

+ [Contributors](#Contributors)

<br>

<br>

<br>

  

## Algorithm

  

The criteria for recommending movies in `VIM PROJECTOR` are `rating` and `count`. `rating` is the average score of a movie that belongs to `genres` you input (`target genres`) and is rated by users belonging to your categories of `gender`, `age`, and `occupation` (`target users`).

`count` is the number of ratings of a movie that belongs to the target genres and rated by the target users.

  

In our algorithms, movies with high rating averages will be selected as recommendations. In each step in the algorithms, rating data of the movies that fit the conditions (`target movies`) will be processed and sorted. Top n movies in the sorted list will be selected to be recommended, with n being the number of movies required.

For a movie to be recommended, the rating average of a movie must be equal or above 4, and the count of the movie must be equal or above 1% of the number of total rating data.

`VIM PROJECTOR` uses two different movie recommendation algorithms depending on different inputs, `user information` and `movie information`.


- Input `user information`
    
By `user information`, we mean your `gender`, `age`, `occupation` and `genres` you wish to watch.

When given `user information`, `VIM PROJECTOR` will recommend movies that are in the `target genres` and have high ratings by `target users`.

The steps in this algorithm are shown below.

1.  Generate a list of 10 recommended movies that are in the `target genres` and are rated highly by `target users`. If `target genres` are not given, the algorithm will search for and recommend movies in all genres.
    
2.  If the number of movies in the generated list is less than 10, then expand the range of `target users` by expanding the range of `occupation` to **all occupations** and search again. Then add the recommended movies to the list.
    
3.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` again by expanding the range of `age` to **all age groups** and search again. Then add the recommended movies to the list.
    
4.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` once more by expanding the range of `gender` to **all genders** and search again. Then add the recommended movies to the list.
    
<br>
  
Let’s see an example of {`gender`: “m”, `age`: “56”, `occupation`: “k-12student”, `genres`: “” } given as an input of `user information`. Since the genres field is not given, movies in all genres will be the candidates for recommendation.

According to this algorithm, the generation of a movie recommendation list would follow the process shown below.

1.  A list of recommended movies is generated using the given `target user` data and `target genres` data. In this example, there is only one `target user`. The number of movies contained in this list of recommended movies is less than 10.
    
2.  Since there are less than 10 movies in the list, expand the range of `target users` by allowing the search for **all occupations**. Now the conditions for the `target users` would be {`gender`: “m”, `age`: “56”, `occupation`: “”, `genres`: “” }. Add the recommended movies based on the new range of `target users`.
    
3.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` by allowing the search for **all age groups**. Now the conditions for the `target users` would be {`gender`: “m”, `age`: “”, `occupation`: “”, `genres`: “” }. Add the recommended movies based on the new range of `target users`.
    
4.  If the number of movies in the generated list is still less than 10, then expand the range of `target users` by allowing the search for **all genders**. Now the conditions for the `target users` would be {`gender`: “”, `age`: “”, `occupation`: “”, `genres`: “” }. Add the recommended movies based on the new range of `target users`.
<br>
<br>
- Input `movie information`
   
By `movie information`, we mean a `movie title` and a `limit number`. When given `movie information`, `VIM PROJECTOR` will recommend high-rated movies similar to the given movie and up to the given `limit number`. The similarity of the movies are assessed by the `genres` of the movies.

The steps in this algorithm are shown below.

1.  Generate a list of recommended movies that are in the **same genres** as the given movie.
    
2.  If the number of movies in the generated list is less than the given `limit number`, add the recommended movies that belong to genres that **include all the genres** of the given movie to the list.
    
3.  If the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **at least one genre** of the given movie.
    
4.  If the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **all genres**.
    

  

Let’s see an example of {`title`: “Toy Story (1995)”, `limit`: 20} given as an input of `movie information`. The `genres` of this movie are `Animation`, `Children's`, and `Comedy`. A list of 20 recommended movies should be generated.

According to this algorithm, the generation of a movie recommendation list would follow the process shown below.

1.  A list of recommended movies is generated from the data of movies that only belong to all three `genres` of `Animation`, `Children's`, and `Comedy` at once. The generated list contains only one movie.
    
2.  Then recommendations are searched within the data of movies that belong to all three `genres` and more. One such example would be a movie that belongs to `genres` of `Animation`, `Children’s`, `Comedy` and `Action`. However, in this case there are no movies added to the list.
    
3.  Now the recommendations are searched from the data of movies that belong to at least one `genre` of the given three `genres`. This means that the recommended movies could belong to any one `genre` from `Animation`, `Children’s`, or `Comedy`, or to any two of these `genres`. In this example, there are 19 movies added to the generated list.
    
4.  In this example, the list of 20 recommended movies is now generated. However, if the number of movies in the generated list is still less than the given `limit number`, add the recommended movies that belong to **all genres**.
   
 
<br>

<br>

<br>

  

## Installation and Run

To install and run this program, download the repository directly, or run the commands below.

```

$ git clone https://github.com/csathenaryu/CSE364_Group10.git

$ git pull origin main

$ git checkout main

$ mvn package

$ java -jar target/CSE364-project-0.0.1-SNAPSHOT/

```

This program can also be installed by running the `run.sh` file that is included in the repository.

`Java(version 11 or up)`, `Git`, ‘curl’ and `Maven` installation must be preceded before installing and running this program.

Run this program by typing in the following command.

```

$ java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Milestone2 "GENDER" "AGE" "OCCUPATION" "GENRES"

```

Let's see an example of a command and the generated recommended movie list. An example input of `user information` is { `gender`: "m", `age`: "60", `occupation`: "retired", `genres`: "Romance"}.

```

$ java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Milestone2 "M" "60" "Retired" "Romance"

```

```

Before Sunrise (1995) (http://www.imdb.com/title/tt0112471)

Much Ado About Nothing (1993) (http://www.imdb.com/title/tt0107616)

Bound (1996) (http://www.imdb.com/title/tt0115736)

Ninotchka (1939) (http://www.imdb.com/title/tt0031725)

Farewell to Arms, A (1932) (http://www.imdb.com/title/tt0022879)

Damsel in Distress, A (1937) (http://www.imdb.com/title/tt0028757)

Diva (1981) (http://www.imdb.com/title/tt0082269)

Wings (1927) (http://www.imdb.com/title/tt0018578)

Say Anything... (1989) (http://www.imdb.com/title/tt0098258)

Blame It on Rio (1984) (http://www.imdb.com/title/tt0086973)

```

  

Please refer to the following sections about details in inputting `user information` or `movie information`.

  

<br>

<br>

  
  

## API Guide

  

### Common

[HTTP Header]

Following items should be set at the HTTP Header to call API.

Name

Required

Value

Content-type

mandatory

application/json

  

[Request]

- Input of GET request should be in JSON type

- Please refer to `list of available input`. The input which is not in the list might be ignored or miscategorized.

- **Capitalization** does not matter. However, in the case of `movie title`, capitalization does matter.

- Please **avoid spelling mistakes.** This program does not provide any fool-proof feature for such mistakes.

- If there is **no valid input** in specific fields, the program will search movies regardless of the fields.
 

### Input User Information

[API Basic Information]

Method

Authentication

URL

Output Format

GET

-

http://localhost:8080/users/recommendations

JSON

  

[Request Parameter]

Name

Type

Required

Value

Description

gender

string

optional

All gender (default)

Wanted gender

age

integer

optional

All age (default)

Wanted age

occupation

string

optional

All occupation (default)

Wanted occupation

genres

string

optional

all genres (default)

Wanted genres

  

[Request Parameter Details]

#### Gender

- **List of available input**: `m`, `f`

  

#### Age

- **List of available input**: `0 or higher`

- **Ages that are not integer or negative** will be ignored.

  

#### Occupation

- **List of available input**: `academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`, `other`

- **Occupations that are not in the list above** will be categorized as `other`.

- For an `occupation` that consists of **more than one word**, omit the space in between and **input as one word**.

  

#### Genres

- **List of available input**: `action`, `adventure`, `animation`, `children's`, `comedy`, `crime`, `documentary`, `drama`, `fantasy`, `film-noir`, `horror`, `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`

- **Genres that are not in the list above** will be ignored when entered.

- In case you are entering **several genres**, please distinguish each genre by delimiter `|`.

- **Repeated inputs** will be ignored.

- Please **do not include spaces** in between genres.

- If there is **no valid genre input**, the program will search movie regardless of genres.

  

[Response Body]

Key

Type

Description

title

string

Titles of recommended movies

genres

string

Genres of recommended movies. Genres are separated by delimiter ‘|’

imdb

string

Link of recommended movies

  

[Sample Request]

curl -X GET http://localhost:8080/users/recommendations -H ‘Content-type:application/json’ -d ‘{“gender”: “F”, “age”: “25”, “occupation”: “Grad student”, “genre”: “Action|War”}’

  

[Sample Response]

[{

“title”: “Toy Story (1995)”,

“genre”: “Animation|Children's|Comedy”,

“imdb”: “https://www.imdb.com/title/tt0114709”

},

{

“title”: “xxx”,

“genres”: “xxx”,

“imdb”: “https://www.imdb.com/title/ttXXXXXXX”

},

{

“title”: “xxx”,

“genres”: “xxx”,

“imdb”: “https://www.imdb.com/title/ttXXXXXXX”

},

...
]
  

<br>

<br>

<br>

### Input Movie Information

[API Basic Information]

Method

Authentication

URL

Output Format

GET

-

http://localhost:8080/movies/recommendations

JSON

  

[Request Parameter]

Name

Type

Required

Value

Description

title

string

mandatory

Title of Movie

ISO-8859-15 encoding required

limit

integer

optional

10 (Default)

Number of recommended movies

  

[Response Body]

Key

Type

Description

title

string

Title of recommended movies

genres

string

Genres of recommended movies. Genres are separated by delimiter ‘|’

imdb

string

Link of recommended movies

  

[Sample Request]

curl -X GET http://localhost:8080/movies/recommendations -H ‘Content-type:application/json’ -d ‘{“title”: “Toy Story (1995)”, “limit”: 20}’

  

[Sample Response]

[{

“title”: “Toy Story (1995)”,

“genre”: “Animation|Children's|Comedy”,

“imdb”: “https://www.imdb.com/title/tt0114709”

},

{

“title”: “xxx”,

“genres”: “xxx”,

“imdb”: “https://www.imdb.com/title/ttXXXXXXX”

},

{

“title”: “xxx”,

“genres”: “xxx”,

“imdb”: “https://www.imdb.com/title/ttXXXXXXX”

},

...
]
  
  

## Contributors

|Student ID|NAME|ROLE|

|:------|:-----|:--------|

|20171113|박소연|[Milestone 1]<br>- Suggested algorithm utilizing boolean ArrayList<br>- Implemented code that receives boolean ArrayList as input and returns Rating as output<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'recommender' package test codes<br>[Milestone 3]<br>- Implemented 'Mileston2Test' and 'Milestone3Test'<br>- Edit 'Milestone2' and 'Milestone3' for include limit parameter|

  

|20171155|안종민|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br>- Edited Pom.xml<br>[Milestone 2]<br>- Implemented 'parsinginputargs' package<br>- Implemented 'loadingdata' package test codes<br>[Milestone 3]<br>- Edit Docker for Milestone3<br>- Implement 'loadingdata' package test codes<br>- Reimplemented 'TopRating' class|

  

|20171375|류성화|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br> - Translated README.md<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'Milestone2.java' test codes<br>[Milestone3]<br>- Applied Spring framework<br>- Implemented 'Milestone3'<br>- Add 'stringStringloadHashFrom' method in 'FilePreprocessing.java' to get genres by title|

  

|20181072|김혜진|[Milestone 1]<br>- Implemented code that receives and parses arguments as inputs<br>- Wrote first draft of README.md<br>[Milestone 2]<br>- Implemented 'loadingdata' package<br>- Implemented 'customdatastructure' and 'parsinginputargs' package test codes<br>[Milestone3]<br>- Implemented 'FilteringDataByCondition'<br>- Implemented 'FilteringDataByCondition' test codes|

  
  

류성화 : Main, Spring, StringString

김혜진 : FilteringDataByCondition 구현, 해당 파일 test 작성,

안종민: Docker, Test작성, TopRating LoadingData 변경

  

<br>

<br>

<br>