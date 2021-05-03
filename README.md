# VIM PROJECTOR (CSE364_Group10)

## Introduction - Milestone 2
`VIM PROJECTOR` is a `movie recommendation program` which recommends movies according to given conditions.  
Type in the `genres` you wish to watch and your `gender`, `age` and `occupation` than you will recieve an `10 recommended movies` of the movies that belong to the given genres.  
Find your next movie to watch with `VIM PROJECTOR`!!!
<br>
<br>
<br>

## Index
+ [Algorithm](#Algorithm)
+ [Introduction](#Introduction)
+ [Index](#Index)
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
(한국어, 이후 번역 예정)

좋은 영화 추천을 위해 우리 영화추천 시스템은 `rating`과 `count` 두가지 조건을 사용한다.

`rating`은 입력된 `gender`, `age`, `occupation` 에 속한 사람들이 입력된 `genre`에 속하는 각 영화의 평균 점수를 말한다.
`count`는 target 유저가 해당 영화에 대해 평가한 데이터의 수를 말한다.

우리는 우선적으로 전체 target rating을 rating이 높은 것이 먼저 오도록 정렬하고, 이 중 추천할 영화 10개를 찾는다.
이 때 `rating`은 4점 이상, `count`는 입력된 `gender`, `age`, `occupation`와 `genre`에 해당하는 모든 rating data 수의 1% 이상의 조건을 만족해야
추천되도록 했다.

만약 그렇게 찾은 총 영화의 개수가 10 보다 작다면, 우리는 `occupation`, `age`, `gender` 순으로 target user의 범위를 넓혀 부족한 개수를 채운다.
예를들어 남자, 65세 이상의 k-12 student 에 해당하는 유저는 한명밖에 없다. 이 경우 추천 영화가 부족하다면 우선 65세 이상의 남자 유저로 범위를 넓힌다.
만약 그래도 충분하지 않다면 다음은 65세 이상에 대해 데이터를 찾고, 이후는 모든 유저에 대해 데이터를 찾는다.



## Installation
To install this program, download the repository directly, or run the commands below.  
```
$ git clone https://github.com/csathenaryu/CSE364_Group10.git
$ git pull origin main
$ mvn install
$ mvn assembly:assembly
``` 
This program can also be installed by running the `run.sh` file that is included in the repository.  
`Java(version 11 or up)`, `Git` and `Maven` installation must be preceded before installing and running this program.  
<br>
<br>
<br>



## Usage
The program will recommend you the `10 movies` and links to imdb according to `gender`, `age`, `occupation` and `genres`.  
Run this program by typing in the following command.   
```
$ java -cp target/CSE364-project-1.0-SNAPSHOT-jar-with-dependencies.jar main.java.Milestone2 "GENDER" "AGE" "OCCUPATION" "GENRES"
```   

For example, let's see the sample command and recommended movie list.  
It is the `romance` movies for the `man` who is `60` and `retired`.
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

Please refer to the following sections about details in inputting `gender`, `age`, `occupation` and `genres`.  
<br>
<br>


### Common
- Please enter `gender`, `age`, `occupation` and `genres` **in the given order**.  
    ```
    "M" "60" "Retired" "Romance"     # right input
    ```
    ```
    "M" "Retired" "60" "Romance"     # wrong input
    ```
- If you want to search movie regardless of particular fields, please leave the part empty.
    ```
    "" "" "Retired" "Romance"        # search for regardless of gender and age
    ```
- Please refer to `list of available input`. The input which is not in the list might be ignored or miscategorized.
- **Capitalization** does not matter.  
- Please **avoid spelling mistakes.** This program does not provide any fool-proof feature for such mistakes.  
- If there is **no valid input** in specific fields, the program will search movie regardless of the fields. 
<br>
<br>

### Gender
- **List of available input**: `m`, `f`
    ```
    "M"               # categorized as 'm'
    ```
- **Genders that are not in the list above** will be ignored.  
    ```
    "male"            # ignored
                      # output: Invalid gender 'male' will be ignored.
    ```
<br>
<br>


### Age
- **List of available input**: `0 or higher`
    ```
    "25"               # right input
    ```
    ```
    "0"                # right input
    ```
- **Ages that are not integer** will be ignored.  
    ```
    "-23"              # ignored
                       # output: Invalid age '-23' will be ignored
    ```
    ```             
    "60.3"             # ignored
                       # output: Invalid age '60.3' will be ignored
    ```
<br>
<br>


### Occupation  
- **List of available input**: `academic`, `educator`, `artist`, `clerical`, `admin`, `collegestudent`, `gradstudent`, `customerservice`, `doctor`,`healthcare`, `executive`, `managerial`, `farmer`, `homemaker`, `k-12student`, `lawyer`, `programmer`, `retired`, `sales`, `marketing`, `scientist`, `self-employed`, `technician`, `engineer`, `tradesman`, `craftsman`, `unemployed`, `writer`  
    ```
    "Academic"              # categorized as 'academic'
    ```
- **Occupations that are not in the list above** will be categorized as `other`.   
    ```
    "dancer"                # categorized as 'other'
                            # output: Invalid occupation 'dancer' will be categorized as 'Other'
    ```
- For an `occupation` that consists of **more than one word**, omit the space in between and **input as one word**.  
    If you put the space in between, it will be categorized as `other`.

    ```
    "college student"       # categorized as 'other'
                            # output: Invalid occupation 'college student' will be categorized as 'Other'
    ```
    If only a part of occupation is entered, it will be categorized as `other`.
    ```
    "student"               # categorized as 'other'
                            # output: Invalid occupation 'student' will be categorized as 'Other'
    ```
<br>
<br>


### Genres  
- **List of available input**: `action`, `adventure`, `animation`, `children's`, `comedy`, `crime`,  `documentary`, `drama`, `fantasy`, `film-noir`, `horror`,  `musical`, `mystery`, `romance`, `scifi`, `thriller`, `war`, `western`  
    ```
    "Action"                # search for 'action'
    ```
- **Genres that are not in the list above** will be ignored when entered.  
    ```
    "sports"                # ignored
                            # output: Invalid genres 'sports' will be ignored
    ```
- In case you are entering **several genres**, please distinguish each genre by delimiter `|`.  
    The program will search for movies that belong genres given as input.  
    ```
    "action|romance"        # search for `action` or 'romance'
    ```
- **Repeated inputs** will be ignored.  
    ```
    action|action|romance   # search for 'action' or 'romance'
    ```
- Please **do not include spaces** in between genres.  
    If a space exists, the genre will be ignored.
    ```
    "action| romance"       # search for 'action'
                            # output: Invalid genres ' romance' will be ignored
    ```
- If there is **no valid genre input**, the program will search movie regardless of genres.  
    ```
    "action |sports"        # search for regardless of genres
                            # output: Invalid genres ' action' will be ignored
                            #         Invalid genres 'sports' will be ignored
    ```
<br>
<br>
<br>



## Contributors
|Student ID|NAME|ROLE|
|:------|:-----|:--------|
|20171113|박소연|[Milestone 1]<br>- Suggested algorithm utilizing boolean ArrayList<br>- Implemented code that receives boolean ArrayList as input and returns Rating as output<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'recommender' package test codes|
|20171155|안종민|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br>- Edited Pom.xml<br>[Milestone 2]<br>- Implemented 'parsinginputargs' package<br>- Implemented 'loadingdata' package test codes|
|20171375|류성화|[Milestone 1]<br>- Implemented code that processes parsed data as a boolean ArrayList<br> - Translated README.md<br>[Milestone 2]<br>- Implemented 'recommender' package<br>- Implemented 'Milestone2.java' test codes|
|20181072|김혜진|[Milestone 1]<br>- Implemented code that receives and parses arguments as inputs<br>- Wrote first draft of README.md<br>[Milestone 2]<br>- Implemented 'loadingdata' package<br>- Implemented 'customdatastructure' and 'parsinginputargs' package test codes|


<br>
<br>
<br>
