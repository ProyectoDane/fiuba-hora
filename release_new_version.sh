# variables
export GRADLE_PATH=./app/build.gradle   # path to the gradle file
export GRADLE_FIELD="versionName"   # field name
# logic
export VERSION_TMP=$(grep $GRADLE_FIELD $GRADLE_PATH | awk '{print $2}')    # get value versionName"0.1.0"
export VERSION=$(echo $VERSION_TMP | sed -e 's/^"//'  -e 's/"$//')  # remove quotes 0.1.0
# result
echo release tag: $VERSION


# user for github
echo "Nombre de usuario para 'https://github.com':"
read GITHUB_USERNAME

# user for github
echo "Contraseña 'https://$GITHUB_USERNAME@github.com':"
read GITHUB_USERPASS

# current branch for push
export CURRENT_BRANCH=$(git branch | grep \* | cut -d ' ' -f2)
export REPO_ORIGIN="https://$GITHUB_USERNAME:$GITHUB_USERPASS@github.com/ProyectoDane/fiuba-hora.git"

# message for tag
echo "Mensaje para git tag $VERSION:" 
read MESSAGE


git commit -am "$MESSAGE"
git push $REPO_ORIGIN $CURRENT_BRANCH
git tag v$VERSION -m "$MESSAGE"
git push $REPO_ORIGIN v$VERSION
