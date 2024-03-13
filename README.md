# Stream recommendation System

## Goal

- Usage of 4 Design Patterns: Singleton, Factory Method, Observer and Command
- Creating a smart recommendation system for a streaming app which can store music, podcasts, audiobooks for each user
- Using CSV files for input using [OpenCSV](https://mvnrepository.com/artifact/com.opencsv/opencsv/5.7.1), getting accustomed to Maven

## Usage
Run ./run.sh args0 args1 args2 args3
Where:
args0 - streamers.csv file
args1 - streams.csv file
args2 - users.csv file
args3 - a test file from any of the /testn/ folders

Example:
./run.sh inputs1/streamers.csv inputs1/streams.csv inputs1/users.csv test1/commands.txt

## Possible commands for streamers:
- \<streamerId\> ADD \<streamType\> \<id\> \<streamGenre\> \<length\> \<name:String\>
- \<streamerId\> LIST - shows a list of streams in JSON format
- \<streamerId\> DELETE \<streamId\>

## Classes:
- Streamers - contains data about the authors of the streams from the streamers.csv file (streamerType, id, name)
- Streams - data about the streams from streams.csv (streamType, id, streamGenre, noOfStreams,streamerId,length,dateAdded,name)
- User - data from users.csv (id,name,streams)

## Possible commands for users:
- \<userId> LIST - shows a list of streams in JSON format
- \<userId> LISTEN \<streamId> - nothing will be printed, but data inside the application will be modified
- \<userId> RECOMMEND (SONG | PODCAST | AUDIOBOOK) - shows 5 new streams from the user's most favorite streamers
- \<userId> SURPRISE (SONG | PODCAST | AUDIOBOOK) - this will show the 3 most recently added streams from the list of streamers who weren't accessed yet by the user


11.03 - Fixed src directory
14.03 - Added maven functionality for OpenCSV
