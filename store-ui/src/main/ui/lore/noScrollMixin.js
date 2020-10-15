export default {
  mounted () {
  	var root = document.getElementsByTagName( 'html' )[0];
  	root.classList.add('no-scroll');
  },

  destroyed () {
    	var root = document.getElementsByTagName( 'html' )[0];
        root.classList.remove('no-scroll');
  }
}