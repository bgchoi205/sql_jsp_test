/* 모바일 사이드 바 */
function mobileSideBar__show(){
  $('.mobile-side-bar').addClass('active');
  $('.mobile-side-bar__content').addClass('active');
  $('html').addClass('mobile-side-bar-actived');
}

function mobileSideBar__hide(){
  $('.mobile-side-bar').removeClass('active');
  $('.mobile-side-bar__content').removeClass('active');
  $('html').removeClass('mobile-side-bar-actived');
}

function mobileSideBar__init(){
  $('.mobile-side-bar__btn').click(function(){
    mobileSideBar__show();
  });
  
  $('.mobile-side-bar__close-btn').click(function(){
    mobileSideBar__hide();
  });
}

mobileSideBar__init();


/* 검색바 */
function searchBar__show(){
  $('.search-bar').addClass('active');
  setTimeout(function(){
	$('.search-bar form div input[name="searchKeyword"]').focus();
	}, 100);
}

function searchBar__hide(){
  $('.search-bar').removeClass('active');
}

function searchBar__init(){
  $('.top-bar__search-btn').click(function(){
    searchBar__show();
  });
  
  $('.search-bar__close-btn, .search-bar').click(function(){
    searchBar__hide();
  });
  
  $('.search-bar > form').click(function(e){
    e.stopPropagation();
  });
  
}

searchBar__init();