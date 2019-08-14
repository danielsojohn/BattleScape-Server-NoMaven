# BattleScape

This is the public repo for the BattleScape RSPS. While this isn't enough to be able to run the server, autocompletion will work fine for the private bits of code.

## Getting Started

These instructions will get you a copy of the project up and running on your local machine for development and testing purposes.

### Prerequisites

[Visual Studio Code](https://code.visualstudio.com) is recommended for development as there are certain files included in this project specificly for Visual Studio Code configuration.

Recommended extensions that you should install inside of Visual Studio Code:
- Java Extension Pack
- Bracket Pair Colorizer

Before starting with Visual Studio Code, you'll need to create your own fork of the project. Start by selecting `fork` in the top-right of this page. Once it finishes, you'll be taken to your forked copy of our repo. Select `clone or download` and copy the url, you'll need this later.

### Installing

Begin by opening Visual Studio Code.

In the menu, go to `terminal -> new terminal`. A terminal window will appear inside of Visial Studio Code.

Next, you'll need to choose where you want to create the project. In the terminal, enter `cd path/where/you/want`. If you just want to use your user's home directory directly, you can skip this step entirely.

You'll need to configure this next command based on your forked repo from earlier. In the same terminal, enter `git clone URL_YOU_SAVED BattleScape/Server`. Replace `URL_YOU_SAVED` with the url you copied earlier.

Select `explorer` in the `sidebar` and select `open directory`. Browse to the location you chose to create the project, enter the BattleScape directory, and select the Server directory.

## Running/Debugging

With only access to the public repo, you'll be unable to run a test server. With this limited access you will still be able to build the server to check for errors (this is automatically done as you make changes), but it'll be up to the administrative team to do any play testing. Alternatively, we can use a beta server to allow users to play test their changes.

There is an ongoing effort to port a large amount of the private repo over to the public repo to allow everyone more access, but there are currently many limitations to what can be changed due to what is publicly available. The reason for the private repo is to limit access to some core classes to prevent anyone from simply creating their own server from BattleScape instead of contributing to it.

## Getting the Latest Changes

Your forked repo won't automatically fetch updates made to the official repo. In the menu, go to `view -> command palette...`. Enter  `Git: Add Remote`. Enter `BattleScape-Server` for the name. Enter `https://github.com/Palidino/BattleScape-Server.git` for the url.

In the menu, go to `view -> command palette...`. Enter `Git: Fetch From All Remotes`.

To pull updates from the official repo into your own, do the following: from `source control` in the `sidebar`, select the three dots on the right-side of source control git (`more actions`). Select `pull from...`, select `BattleScape-Server`, and then select `BattleScape-Server/master`.

## Contributing

Once you've completed change(s), push them into your forked repo by going to `source control` in the `sidebar`. From here, you'll need to enter a message briefly describing the changes, and select the checkmark (`commit`). From there, select the refresh symbol (`synchronize changes`). This will save your changes into your forked repo, not the official repo.

To request your changes to be added to the official repo, visit your forked repo on GitHub. Select `new pull request`, and then submit your changes from there.
