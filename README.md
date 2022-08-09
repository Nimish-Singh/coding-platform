# coding-platform
An assignment to design a coding platform with the given functionalities, making use of OOPS concepts

# TODO

* Use Command pattern for each of the user functions
* Add unit tests
* Work on bonus functionality
  * Contest history
    * Create a new model `UserContestStats` containing `Integer score` and `List<Question> questionsSolved`
    * Replace `Map<User, Integer> participantScoreMap` with `List<Map<User, UserContestStats>> participantContestHistory` in `Contest` model.
    * Keep adding contest scores from each run into this list.
    * For the current contest status/leaderboard, access the last element of the list
  * Contest withdraw functionality
    * Use `ContestState` enum to check the contest state
    * If it is not `STARTED` then remove the given user from its participant collection 