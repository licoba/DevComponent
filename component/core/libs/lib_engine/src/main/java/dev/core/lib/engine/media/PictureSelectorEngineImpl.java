package dev.core.lib.engine.media;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;

import androidx.fragment.app.Fragment;

import com.luck.picture.lib.PictureSelectionModel;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.entity.LocalMedia;
import com.luck.picture.lib.tools.PictureFileUtils;

import java.util.ArrayList;
import java.util.List;

import dev.engine.media.IMediaEngine;
import dev.utils.LogPrintUtils;

/**
 * detail: PictureSelector Media Selector Engine 实现
 * @author Ttt
 */
public class PictureSelectorEngineImpl
        implements IMediaEngine<MediaConfig, LocalMediaData> {

    // 日志 TAG
    private final String      TAG                   = PictureSelectorEngineImpl.class.getSimpleName();
    // 全局请求跳转回传 code
    private final int         PIC_REQUEST_CODE      = 159857;
    // 全局配置信息
    private final MediaConfig PIC_CONFIG            = new MediaConfig();
    // 拍照保存地址
    private       String      CAMERA_SAVE_PATH      = null;
    // 压缩图片保存地址
    private       String      COMPRESS_SAVE_PATH    = null;
    // 图片大于多少才进行压缩 (kb)
    private       int         MINIMUM_COMPRESS_SIZE = 0;

    // ===============
    // = 对外公开方法 =
    // ===============

    @Override
    public boolean openCamera(Activity activity) {
        return openCamera(activity, PIC_CONFIG);
    }

    @Override
    public boolean openCamera(
            Activity activity,
            MediaConfig config
    ) {
        PictureSelectionModel pictureSelectionModel = getPictureSelectionModel(
                getPictureSelector(activity, null), config, true
        );
        return forResult(pictureSelectionModel);
    }

    @Override
    public boolean openCamera(Fragment fragment) {
        return openCamera(fragment, PIC_CONFIG);
    }

    @Override
    public boolean openCamera(
            Fragment fragment,
            MediaConfig config
    ) {
        PictureSelectionModel pictureSelectionModel = getPictureSelectionModel(
                getPictureSelector(null, fragment), config, true
        );
        return forResult(pictureSelectionModel);
    }

    // =

    @Override
    public boolean openGallery(Activity activity) {
        return openGallery(activity, PIC_CONFIG);
    }

    @Override
    public boolean openGallery(
            Activity activity,
            MediaConfig config
    ) {
        PictureSelectionModel pictureSelectionModel = getPictureSelectionModel(
                getPictureSelector(activity, null), config, false
        );
        return forResult(pictureSelectionModel);
    }

    @Override
    public boolean openGallery(Fragment fragment) {
        return openGallery(fragment, PIC_CONFIG);
    }

    @Override
    public boolean openGallery(
            Fragment fragment,
            MediaConfig config
    ) {
        PictureSelectionModel pictureSelectionModel = getPictureSelectionModel(
                getPictureSelector(null, fragment), config, false
        );
        return forResult(pictureSelectionModel);
    }

    // ===========
    // = 配置方法 =
    // ===========

    @Override
    public MediaConfig getConfig() {
        return PIC_CONFIG;
    }

    @Override
    public void setConfig(MediaConfig config) {
        PIC_CONFIG.set(config);
    }

    @Override
    public String getCameraSavePath() {
        return CAMERA_SAVE_PATH;
    }

    @Override
    public String getCompressSavePath() {
        return COMPRESS_SAVE_PATH;
    }

    @Override
    public void setSavePath(
            String cameraSavePath,
            String compressSavePath
    ) {
        CAMERA_SAVE_PATH = cameraSavePath;
        COMPRESS_SAVE_PATH = compressSavePath;
        // 设置配置
        PIC_CONFIG.setCameraSavePath(cameraSavePath)
                .setCompressSavePath(compressSavePath);
    }

    @Override
    public int getMinimumCompressSize() {
        return MINIMUM_COMPRESS_SIZE;
    }

    @Override
    public void setMinimumCompressSize(int minimumCompressSize) {
        MINIMUM_COMPRESS_SIZE = minimumCompressSize;
        // 设置配置
        PIC_CONFIG.setMinimumCompressSize(minimumCompressSize);
    }

    // ===========
    // = 其他方法 =
    // ===========

    @Override
    public void deleteCacheDirFile(
            Context context,
            int type
    ) {
        try {
            PictureFileUtils.deleteCacheDirFile(context, type);
        } catch (Exception e) {
            LogPrintUtils.eTag(TAG, e, "deleteCacheDirFile");
        }
    }

    @Override
    public void deleteAllCacheDirFile(Context context) {
        try {
            PictureFileUtils.deleteAllCacheDirFile(context);
        } catch (Exception e) {
            LogPrintUtils.eTag(TAG, e, "deleteAllCacheDirFile");
        }
    }

    @Override
    public boolean isMediaSelectorResult(
            int requestCode,
            int resultCode
    ) {
        return requestCode == PIC_REQUEST_CODE && resultCode == Activity.RESULT_OK;
    }

    // =

    @Override
    public List<LocalMediaData> getSelectors(Intent data) {
        List<LocalMedia>     result = PictureSelector.obtainMultipleResult(data);
        List<LocalMediaData> lists  = new ArrayList<>();
        if (result != null) {
            for (LocalMedia localMedia : result) {
                if (localMedia != null) {
                    lists.add(new LocalMediaData(localMedia));
                }
            }
        }
        return lists;
    }

    @Override
    public List<String> getSelectorPaths(
            Intent data,
            boolean original
    ) {
        List<LocalMediaData> result = getSelectors(data);
        List<String>         lists  = new ArrayList<>();
        if (result != null) {
            for (LocalMediaData media : result) {
                if (media != null) {
                    String path = media.getLocalMediaPath(original);
                    lists.add(path);
                }
            }
        }
        return lists;
    }

    @Override
    public LocalMediaData getSingleSelector(Intent data) {
        List<LocalMediaData> lists = getSelectors(data);
        if (lists != null && lists.size() > 0) return lists.get(0);
        return null;
    }

    @Override
    public String getSingleSelectorPath(
            Intent data,
            boolean original
    ) {
        List<String> lists = getSelectorPaths(data, original);
        if (lists != null && lists.size() > 0) return lists.get(0);
        return null;
    }

    // ===========
    // = 内部处理 =
    // ===========

    /**
     * 获取图片选择器对象
     * @param activity {@link Activity}
     * @param fragment {@link Fragment}
     * @return {@link PictureSelector}
     */
    private PictureSelector getPictureSelector(
            final Activity activity,
            final Fragment fragment
    ) {
        if (activity != null) {
            return PictureSelector.create(activity);
        } else if (fragment != null) {
            return PictureSelector.create(fragment);
        }
        return null;
    }

    /**
     * 是否跳转成功
     * @param pictureSelectionModel 图片选择配置模型
     * @return {@code true} success, {@code false} fail
     */
    private boolean forResult(final PictureSelectionModel pictureSelectionModel) {
        if (pictureSelectionModel != null) {
            pictureSelectionModel.forResult(PIC_REQUEST_CODE);
            return true;
        }
        return false;
    }

    /**
     * 获取图片选择配置模型
     * <pre>
     *     // 结果回调 onActivityResult requestCode
     *     pictureSelectionModel.forResult(requestCode);
     * </pre>
     * @param pictureSelector {@link PictureSelector}
     * @param config          {@link MediaConfig}
     * @param isCamera        是否拍照
     * @return {@link PictureSelectionModel}
     */
    private PictureSelectionModel getPictureSelectionModel(
            final PictureSelector pictureSelector,
            final MediaConfig config,
            final boolean isCamera
    ) {
        if (pictureSelector != null && config != null) {
            // 图片选择配置模型
            PictureSelectionModel pictureSelectionModel;
            // 相册选择类型
            if (isCamera) {
                pictureSelectionModel = pictureSelector.openCamera(config.getMimeType());
            } else {
                pictureSelectionModel = pictureSelector.openGallery(config.getMimeType());
            }

            // 是否裁减
            boolean isCrop = config.isCrop();
            // 是否圆形裁减
            boolean isCircleCrop = config.isCircleCrop();

            // 多选 or 单选 MediaConfig.MULTIPLE or MediaConfig.SINGLE
            pictureSelectionModel.selectionMode(config.getSelectionMode())
                    .imageEngine(GlideEngine.createGlideEngine())
                    .isPreviewImage(true) // 是否可预览图片 true or false
                    .isPreviewVideo(true) // 是否可以预览视频 true or false
                    .isEnablePreviewAudio(true) // 是否可播放音频 true or false
                    .isZoomAnim(true) // 图片列表点击 缩放效果 默认 true
                    .isPreviewEggs(true) // 预览图片时是否增强左右滑动图片体验 ( 图片滑动一半即可看到上一张是否选中 ) true or false
                    .imageSpanCount(config.getImageSpanCount())// 每行显示个数 int
                    .minSelectNum(config.getMinSelectNum()) // 最小选择数量 int
                    .maxSelectNum(config.getMaxSelectNum()) // 最大图片选择数量 int
                    .isCamera(config.isCamera()) // 是否显示拍照按钮 true or false
                    .isGif(config.isGif()) // 是否显示 Gif true or false
                    // = 压缩相关 =
                    .isCompress(config.isCompress()) // 是否压缩 true or false
                    .minimumCompressSize(config.getMinimumCompressSize()) // 小于 xxkb 的图片不压缩
                    .withAspectRatio(config.getWithAspectRatio()[0], config.getWithAspectRatio()[1]) // 裁剪比例 如 16:9 3:2 3:4 1:1 可自定义
                    // = 裁减相关 =
                    // 判断是否显示圆形裁减
                    .circleDimmedLayer(isCircleCrop)
                    // = 裁减配置 =
                    .isEnableCrop(isCrop) // 是否裁剪 true or false
                    .freeStyleCropEnabled(isCrop) // 裁剪框是否可拖拽 true or false
                    .showCropFrame(!isCircleCrop && isCrop) // 是否显示裁剪矩形边框 圆形裁剪时建议设为 false
                    .showCropGrid(!isCircleCrop && isCrop) // 是否显示裁剪矩形网格 圆形裁剪时建议设为 false
                    .rotateEnabled(isCrop) // 裁剪是否可旋转图片 true or false
                    .scaleEnabled(isCrop); // 裁剪是否可放大缩小图片 true or false

            // 设置拍照保存地址
            if (!TextUtils.isEmpty(config.getCameraSavePath())) {
                pictureSelectionModel.setOutputCameraPath(config.getCameraSavePath());
            }
            // 设置压缩图片保存地址
            if (!TextUtils.isEmpty(config.getCompressSavePath())) {
                pictureSelectionModel.compressSavePath(config.getCompressSavePath());
            }
            // 判断是否存在选中资源
            if (config.getLocalMedia() != null && config.getLocalMedia().size() != 0) {
                pictureSelectionModel.selectionData(
                        convertList(config.getLocalMedia())
                );
            }
            return pictureSelectionModel;
        }
        return null;
    }

    /**
     * 转换 List
     * @param lists {@link LocalMediaData} list
     * @return {@link LocalMedia} list
     */
    private List<LocalMedia> convertList(final List<LocalMediaData> lists) {
        List<LocalMedia> medias = new ArrayList<>();
        if (lists != null) {
            for (LocalMediaData data : lists) {
                if (data != null && data.getLocalMedia() != null) {
                    medias.add(data.getLocalMedia());
                }
            }
        }
        return medias;
    }
}