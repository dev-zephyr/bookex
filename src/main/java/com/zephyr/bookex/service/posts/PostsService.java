package com.zephyr.bookex.service.posts;

import com.zephyr.bookex.domain.posts.Posts;
import com.zephyr.bookex.domain.posts.PostsRepository;
import com.zephyr.bookex.web.dto.PostsListResponseDto;
import com.zephyr.bookex.web.dto.PostsResponseDto;
import com.zephyr.bookex.web.dto.PostsSaveRequestDto;
import com.zephyr.bookex.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts posts = postsRepository.findById(id)
                                    .orElseThrow(() ->
                                            new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        posts.update(requestDto.getTitle(), requestDto.getContent());

        return id;
    }

    public PostsResponseDto findById(Long id) {
        Posts entity = postsRepository.findById(id)
                                    .orElseThrow(() ->
                                        new IllegalArgumentException("해당 게시글이 없습니다. id=" + id)
                                    );

        return new PostsResponseDto(entity);
    }

    // 트랜잭션 범위는 유지하되, 조회 기능만 남겨두어 조회 속도 개선.
    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAlldesc().stream()
                    .map(PostsListResponseDto::new)
                    .collect(Collectors.toList());
    }

    public void delete(Long id) {
        Posts posts = postsRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id=" + id));

        postsRepository.delete(posts);

    }
}
