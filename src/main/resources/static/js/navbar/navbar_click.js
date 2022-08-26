let bookmark_item = document.querySelector('#bookmark-span');
bookmark_item.addEventListener('click', ()=>{
    let target = document.querySelector('#bookmark-item');
    let sub_target = document.querySelector('#my-notice-channel-item');

    valid_menu(target, sub_target)
})

let notice_channel_span = document.querySelector('#notice-channel-span');
notice_channel_span.addEventListener('click', ()=>{
    let target = document.querySelector('#my-notice-channel-item');
    let sub_target = document.querySelector('#bookmark-item');
    let target_class = target.getAttribute('class');

    if (target_class == null || target_class.length <= 11){
        render_menu(target, sub_target);
    }else{
        remove_menu(target);
    }
})

function valid_menu(target, sub_target){
    let target_class = target.getAttribute('class');
    if (target_class == null || target_class.length <= 11){
        render_menu(target, sub_target);
    }else{
        remove_menu(target);
    }
}

function render_menu(target, sub_target){

    sub_target.classList.remove('item-display');
    sub_target.style = 'display:none';

    target.classList.add('item-display');
    target.style = 'display:block';

    let inner = document.querySelector('#inner');
    inner.classList.add('click-menu');

    let navbar = document.querySelector('#navbar-inner');
    navbar.classList.add('click-navbar');
}

function remove_menu(target){

    target.classList.remove('item-display');
    target.style = 'display:none';

    let inner = document.querySelector('#inner');
    inner.classList.remove('click-menu');

    let navbar = document.querySelector('#navbar-inner');
    navbar.classList.remove('click-navbar');
}