module.exports = (grunt) ->
  grunt.initConfig
    pkg: grunt.file.readJSON 'package.json'

    watch:
      src:
        files: 'src/**/*.coffee'
        tasks: ['default']

    coffee:
      compile:
        expand: true
        cwd: 'src/'
        src: '**/*.coffee'
        dest: 'js/'
        ext: '.js'

    clean: ['js/']

  grunt.loadNpmTasks 'grunt-contrib-watch'
  grunt.loadNpmTasks 'grunt-contrib-coffee'
  grunt.loadNpmTasks 'grunt-contrib-clean'

  grunt.registerTask 'default', ['clean', 'coffee:compile']
  grunt.registerTask 'demon', ['default', 'watch:src']
