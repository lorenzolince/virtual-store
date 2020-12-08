import Vue from 'vue'

export function truncate(text, length, suffix){
    return text.substring(0, length) + suffix;
}

const filters = {truncate}

Object.keys(filters).forEach(key => {
    Vue.filter(key, filters[key])
})

export default filters