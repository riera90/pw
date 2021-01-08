function filterBy(key, value){
    let posts = document.getElementById("postsContainer").children;
    for (let i = 0; i < posts.length; i++) {
        if (posts[i].querySelector("#"+key).innerHTML.toString() !== value.toString()) {
            document.getElementById("postsContainer").children[i].style.display="none";
        }
    }
    return true;
}


function filterByLike(key, value){
    let posts = document.getElementById("postsContainer").children;
    for (let i = 0; i < posts.length; i++) {
        if (posts[i].querySelector("#"+key).innerHTML.toString().indexOf(value) === -1) {
            document.getElementById("postsContainer").children[i].style.display="none";
        }
    }
    return true;
}


function resetFilter(){
    for (let i = 0; i < document.getElementById("postsContainer").children.length; i++)
        document.getElementById("postsContainer").children[i].style.display="block";
    return true;
}


function filter(){
    resetFilter();
    if (document.getElementById("typeFilter").value !== "")
        filterBy("type", document.getElementById("typeFilter").value);
    if (document.getElementById("titleFilter").value !== "")
        filterByLike("title", document.getElementById("titleFilter").value);
    if (document.getElementById("topicFilter").value !== "")
        filterByLike("topics", document.getElementById("topicFilter").value);
}