package afkt_replace.standard.function

object Code {

    /**
     * 是否隐藏文件、文件夹判断
     * @param path 待判断文件路径
     * @return `true` yes, `false` no
     */
    fun isHidden(path: String?): Boolean {
        if (path != null) {
            val temp = path.replace("/../..".toRegex(), "")
                .replace("\\...\\...".toRegex(), "")
//                .replace("\\..\\..", "");
            return temp.contains("\\.")
        }
        return false
    }
}