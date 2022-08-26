$(document).ready(()=>{
    document.querySelector('#theme-button').addEventListener('click', toggleTheme);

    if(document.querySelector('body').className === 'theme-dark')
        toDark()
    else
        toLight()
})

function recallTheme(){
    let target = document.querySelector('body');
    if (target.className === 'theme-light')
        toLight()
    else
        toDark()
}

function toggleTheme(){
    let target = document.querySelector('body');
    if (target.className === 'theme-light')
        toDark()
    else
        toLight()
}

function toLight(){

    let theme_button = document.querySelector('#theme-button');

    theme_button.classList.add('light');
    theme_button.classList.remove('dark');


    theme_button.classList.add('axi-moon2');
    theme_button.classList.remove('axi-sun4');

    let a_s = document.querySelectorAll('a');
    for (let i = 0; i < a_s.length; i++) {
        if (a_s[i].classList.contains('channel-imgs'))
            continue;

        a_s[i].classList.add('theme-light');
        a_s[i].classList.remove('theme-dark');
    }

    let body = document.querySelector('body');
    body.classList.add('theme-light');
    body.classList.remove('theme-dark')

    let navbar = document.querySelector('#navbar');
    navbar.classList.add('theme-light')
    navbar.classList.remove('theme-dark')

    try{
        let form_items = document.querySelectorAll('.notice-form-items');
        for (let i = 0; i < form_items.length; i++) {
            form_items[i].classList.add('form-theme-light')
            form_items[i].classList.remove('form-theme-dark')
        }
    }catch(e){

    }

    try{
        let notice_type_change = document.querySelector('#notice-type-change');
        notice_type_change.classList.add('theme-light')
        notice_type_change.classList.remove('theme-dark')
    }catch (e){
        console.log(e)
    }

    try{


        let card_items = document.querySelectorAll('.card-item');
        for (let i = 0; i < card_items.length; i++) {
            card_items[i].classList.add('theme-light');
            card_items[i].classList.remove('theme-dark');
        }

        let bookmark_item = document.querySelector('#bookmark-item');
        bookmark_item.classList.add('theme-light')
        bookmark_item.classList.remove('theme-dark')

        let my_notice_channel = document.querySelector('#my-notice-channel-item');
        my_notice_channel.classList.add('theme-light')
        my_notice_channel.classList.remove('theme-dark')

        let show_more = document.querySelector('#show-more');
        show_more.classList.add('theme-light')
        show_more.classList.remove('theme-dark')
    }catch (e){
        console.log(e)
    }



    ajaxSettingTheme('light')
}

function toDark(){

    let theme_button = document.querySelector('#theme-button');

    theme_button.classList.add('dark');
    theme_button.classList.remove('light');


    theme_button.classList.add('axi-sun4');
    theme_button.classList.remove('axi-moon2');

    let a_s = document.querySelectorAll('a');
    for (let i = 0; i < a_s.length; i++) {
        if (a_s[i].classList.contains('channel-imgs'))
            continue;

        a_s[i].classList.add('theme-dark');
        a_s[i].classList.remove('theme-light');
    }

    let body = document.querySelector('body');
    body.classList.add('theme-dark')
    body.classList.remove('theme-light');

    let navbar = document.querySelector('#navbar');
    navbar.classList.add('theme-dark')
    navbar.classList.remove('theme-light')

    let form_items = document.querySelectorAll('.notice-form-items ');
    for (let i = 0; i < form_items.length; i++) {
        form_items[i].classList.add('form-theme-dark')
        form_items[i].classList.remove('form-theme-light')
    }

    try{
        let notice_type_change = document.querySelector('#notice-type-change');
        notice_type_change.classList.add('theme-dark')
        notice_type_change.classList.remove('theme-light')
    }catch (e){
        console.log(e)
    }

    try{

        let card_items = document.querySelectorAll('.card-item');
        for (let i = 0; i < card_items.length; i++) {
            card_items[i].classList.add('theme-dark');
            card_items[i].classList.remove('theme-light');
        }

        let bookmark_item = document.querySelector('#bookmark-item');
        bookmark_item.classList.add('theme-dark')
        bookmark_item.classList.remove('theme-light')

        let my_notice_channel = document.querySelector('#my-notice-channel-item');
        my_notice_channel.classList.add('theme-dark')
        my_notice_channel.classList.remove('theme-light')

        let show_more = document.querySelector('#show-more');
        show_more.classList.add('theme-dark')
        show_more.classList.remove('theme-light')
    }catch(e){
        console.log(e)
    }



    ajaxSettingTheme('dark')
}

function ajaxSettingTheme(current){

    $.ajax({
        url: "/setting/theme",
        data: {'theme': current},
        type: "POST",
        success : function(data){
            console.log('>>> '+data)
        },
        error: function(errorThrown) {
            alert("잘못된 요청입니다.");
        }
    });
}